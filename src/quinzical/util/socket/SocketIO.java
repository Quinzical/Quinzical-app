package quinzical.util.socket;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import quinzical.controllers.online.LobbyScreenController;
import quinzical.controllers.util.alerts.WarningAlert;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.EngineIOException;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

/**
 * This class is used to interact with SocketIO and multiplayer
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class SocketIO {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private static SocketIO _instance;
    private static final String SOCKET_ENDPOINT = "https://quinzical-api.herokuapp.com/";
    private static final int SHOW_RESULT = 3000;

    private Socket _socket;
    private Chat _chat;

    private String _username;
    private HashMap<String, String> _users;
    private JSONObject _room;
    private boolean _playing;
    private boolean _correct;
    private boolean _win;
    private String _question;
    private String _qualifier;
    private String _answer;
    private String _winner;

    private LobbyScreenController _lobbyListener;

    private SocketIO() {
        System.out.println("setting up SocketIO");
        try {
            _socket = IO.socket(SOCKET_ENDPOINT);

            _socket.on("users", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    JSONObject obj = (JSONObject) args[0];
                    Iterator<String> keys = obj.keys();
                    _users = new HashMap<String, String>();

                    while (keys.hasNext()) {
                        String key = keys.next();
                        _users.put(key, obj.getString(key));
                    }
                }
            });
            _socket.on("createRoom", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    _playing = true;
                    _room = (JSONObject) args[0];
                    Platform.runLater(() -> {
                        _sceneManager.cleanSwitchScene(Scenes.LOBBY_SCREEN);
                    });
                }
            });
            _socket.on("joinRoom", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    _playing = true;
                    _room = (JSONObject) args[0];
                    Platform.runLater(() -> {
                        if (_sceneManager.getCurrentScene() == Scenes.LOBBY_SCREEN) {
                            _lobbyListener.updateUsers();
                            return;
                        }
                    });
                }
            });
            _socket.on("startingGame", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    if (_playing) {
                        Platform.runLater(() -> {
                            _sceneManager.cleanSwitchScene(Scenes.COUNTDOWN);
                        });
                    }
                }
            });
            _socket.on("restartRoom", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    _playing = true;
                    _room = (JSONObject) args[0];
                    Platform.runLater(() -> {
                        _sceneManager.cleanSwitchScene(Scenes.LOBBY_SCREEN);
                    });
                }
            });
            _socket.on("question", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    if (_playing) {
                        JSONObject obj = (JSONObject) args[0];
                        _question = obj.getString("question");
                        _qualifier = obj.getString("qualifier");
                        _answer = obj.getString("answer");
                        Platform.runLater(() -> {
                            _sceneManager.cleanSwitchScene(Scenes.ONLINE_QUESTION);
                        });
                    }
                }
            });
            _socket.on("end", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    if (_playing || _sceneManager.getCurrentScene() != Scenes.REMAINING) {
                        checkResult((JSONObject) args[0]);
                        Platform.runLater(() -> {
                            _sceneManager.cleanSwitchScene(Scenes.GAME_OVER);
                        });
                    }
                }
            });
            _socket.on("win", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    if (_playing || _sceneManager.getCurrentScene() != Scenes.REMAINING) {
                        checkResult((JSONObject) args[0]);
                        _win = true;
                        _winner = _room.getJSONArray("correct").getString(0);
                        Platform.runLater(() -> {
                            _sceneManager.cleanSwitchScene(Scenes.GAME_OVER);
                        });
                    }
                }
            });
            _socket.on("tie", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    if (_playing || _sceneManager.getCurrentScene() != Scenes.REMAINING) {
                        checkResult((JSONObject) args[0]);
                        _win = false;
                        Platform.runLater(() -> {
                            _sceneManager.cleanSwitchScene(Scenes.GAME_OVER);
                        });
                    }
                }
            });
            _socket.on("error", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Platform.runLater(() -> {
                        if (args[0] instanceof EngineIOException) {
                            System.out.println(args[0]);
                            new WarningAlert("Server disconnected");
                            _sceneManager.cleanSwitchScene(Scenes.OPENING_MENU);
                            return;
                        }
                        new WarningAlert((String) args[0]);
                        _sceneManager.cleanSwitchScene(Scenes.ONLINE_MENU);
                    });
                }
            });
            _socket.on("connect_failed", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Platform.runLater(() -> {
                        new WarningAlert("Server connection failed");
                        _sceneManager.cleanSwitchScene(Scenes.OPENING_MENU);
                    });
                }
            });
            _socket.on("disconnect", new Emitter.Listener() {
                @Override
                public void call(final Object... args) {
                    Platform.runLater(() -> {
                        new WarningAlert("Server disconnected");
                        _sceneManager.cleanSwitchScene(Scenes.OPENING_MENU);
                    });
                }
            });

            _chat = new Chat(_socket);
            _socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return QuestionModel
     */
    public static SocketIO getInstance() {
        if (_instance == null) {
            _instance = new SocketIO();
        }
        return _instance;
    }

    /**
     * Used to set username using socket io
     * 
     * @param username
     */
    public void setUsername(final String username) {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        _socket.emit("username", obj);
    }

    /**
     * Used to create a room/lobby using socket io
     * 
     * @param username
     * @param timer
     * @param international
     */
    public void createRoom(final String username, final int timer, final boolean international) {
        JSONObject obj = new JSONObject();
        obj.put("timer", timer);
        obj.put("international", international);
        _socket.emit("createRoom", obj);
    }

    /**
     * Used to start hosted lobby using socket io
     * 
     * @param code
     */
    public void startGame(final String code) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        _socket.emit("startGame", obj);
    }

    /**
     * Used to join a room using room code
     * 
     * @param code
     */
    public void joinRoom(final String code) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        _socket.emit("joinRoom", obj);
    }

    /**
     * Used to restart a game
     * 
     * @param code
     */
    public void restartRoom(final String code) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        _socket.emit("restartRoom", obj);
    }

    /**
     * Used to send an answer to a question using socket io
     * 
     * @param code
     * @param answer
     */
    public void sendAnswer(final String code, final String answer) {
        JSONObject obj = new JSONObject();
        obj.put("code", code);
        obj.put("answer", answer);
        _socket.emit("answer", obj);
    }

    /**
     * Used to disconnect sockets
     */
    public void disconnect() {
        _socket.disconnect();
    }

    /**
     * Used to leave current Room
     */
    public void leaveRoom() {
        JSONObject obj = new JSONObject();
        obj.put("code", getRoom().getString("code"));
        _socket.emit("leaveRoom", obj);
    }

    /**
     * Used to set question from socket io
     * 
     * @param question
     * @param qualifier
     * @param answer
     */
    public void setQuestion(final String question, final String qualifier, final String answer) {
        _question = question;
        _qualifier = qualifier;
        _answer = answer;
    }

    /**
     * Used to get current room data
     * 
     * @return room
     */
    public JSONObject getRoom() {
        return _room;
    }

    /**
     * Used to get all users
     * 
     * @return users
     */
    public HashMap<String, String> getUsers() {
        return _users;
    }

    /**
     * Used to get socket id
     * 
     * @return socket id
     */
    public String getSocketID() {
        return _socket.id();
    }

    /**
     * Used to set lobby listener so we can update users changes
     * 
     * @param lobbyListener
     */
    public void setLobbyListener(final LobbyScreenController lobbyListener) {
        _lobbyListener = lobbyListener;
    }

    /**
     * Used to check if a string exist in a JSON array
     * 
     * @param array
     * @param exist
     * @return exists
     */
    private boolean exist(final JSONArray array, final String exist) {
        for (int i = 0; i < array.length(); i++) {
            if (exist == array.getString(i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to get qualifier
     * 
     * @return qualifier
     */
    public String getQualifier() {
        return _qualifier;
    }

    /**
     * Used to get question
     * 
     * @return question
     */
    public String getQuestion() {
        return _question;
    }

    /**
     * Used to get if it's win or tie
     * 
     * @return win
     */
    public boolean getWin() {
        return _win;
    }

    /**
     * Used to get winner
     * 
     * @return winner
     */
    public String getWinner() {
        return _winner;
    }

    /**
     * Used to get answer
     * 
     * @return answer
     */
    public String getAnswer() {
        return _answer;
    }

    /**
     * Used to get correct
     * 
     * @return correct
     */
    public boolean getCorrect() {
        return _correct;
    }

    /**
     * Used to get playing
     * 
     * @return playing
     */
    public boolean isPlaying() {
        return _playing;
    }

    /**
     * Used to set message scroll pane
     * 
     * @param pane
     */
    public void setPane(final VBox pane) {
        _chat.setPane(pane);
        _chat.history();
    }

    private void checkResult(final JSONObject room) {
        if (exist(_room.getJSONArray("correct"), _username)) {
            _correct = true;
        } else {
            _correct = false;
            _playing = false;
        }
        Platform.runLater(() -> {
            _sceneManager.cleanSwitchScene(Scenes.EJECT);
        });
        _room = room;
        try {
            Thread.sleep(SHOW_RESULT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
