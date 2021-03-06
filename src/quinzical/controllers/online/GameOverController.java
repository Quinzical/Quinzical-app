package quinzical.controllers.online;

import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.socket.SocketIO;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 * This class is used when the game is over e.g tie or win
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameOverController {

    private final SocketIO _socket = SocketIO.getInstance();
    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Label _header;

    @FXML
    private Label _username;

    @FXML
    private TilePane _users;

    @FXML
    private Button _next;

    /**
     * initialize with GameOver.fxml
     */
    public void initialize() {
        if (!_socket.getRoom().getString("host").equals(_socket.getSocketID())) {
            _next.setDisable(true);
        }

        if (_socket.getWin()) {
            _header.setText("Winner");
            HashMap<String, String> allUsers = _socket.getUsers();
            _username.setText(allUsers.get(_socket.getWinner()));

            if (_socket.getWinner().equals(_socket.getSocketID())) {
                _username.getStyleClass().add("logingreen");
            } else {
                _username.getStyleClass().add("login");
            }
        } else {
            _header.setText("GameOver");
            _username.setText("TIE");
        }
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _socket.leaveRoom();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handlePlayAgain(final ActionEvent event) {
        _socket.restartRoom(_socket.getRoom().getString("code"));
    }
}
