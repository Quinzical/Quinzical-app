package quinzical.controllers.online;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import quinzical.controllers.util.alerts.QRCodeAlert;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.socket.SocketIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 * This class is used for lobby screen for online game.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LobbyScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();

    private static final String WEBSITE = "https://quinzical.gq/";

    private static final int TIMER_DIVISION = 1000;

    @FXML
    private Label _header;

    @FXML
    private Label _timer;

    @FXML
    private Label _international;
    
    @FXML
    private Button _start;

    @FXML
    private TilePane _users;

    /**
     * initialize with LobbyScreen.fxml
     */
    public void initialize() {
        JSONObject room = _socket.getRoom();
        _timer.setText(String.valueOf(room.getInt("timer") / TIMER_DIVISION));
        _header.setText("Lobby: " + room.getString("code"));

        if (room.getBoolean("international")) {
            _international.setText("TRUE");
        } else {
            _international.setText("FALSE");
        }

        if (!room.getString("host").equals(_socket.getSocketID())){
            _start.setDisable(true);
        }

        updateUsers();

        _socket.setLobbyListener(this);
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _socket.leaveRoom();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handleConfirm(final ActionEvent event) {
        JSONObject room = _socket.getRoom();
        _socket.startGame(room.getString("code"));
    }

    @FXML
    private void handleQRCode(final ActionEvent event) {
        new QRCodeAlert(WEBSITE + _socket.getRoom().getString("code"));
    }

    /**
     * Update users on the screen inside lobby on update
     */
    public void updateUsers() {
        JSONObject room = _socket.getRoom();
        JSONArray users = room.getJSONArray("users");
        HashMap<String, String> allUsers = _socket.getUsers();
        _users.getChildren().clear();

        for (int i = 0; i < users.length(); i++) {
            String socketID = users.getString(i);
            Label user = new Label(allUsers.get(socketID));

            if (socketID.equals(_socket.getSocketID())) {
                user.getStyleClass().add("logingreen");
            } else {
                user.getStyleClass().add("login");
            }

            _users.getChildren().add(user);
        }
    }

}
