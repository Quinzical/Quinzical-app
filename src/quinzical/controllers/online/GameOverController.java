package quinzical.controllers;



import quinzical.controllers.helper.StarBackground;
import quinzical.helper.SceneManager;
import quinzical.helper.SceneManager.Scenes;
import quinzical.models.socket.SocketIO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with GameOver.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        if (_socket.getWin()) {
            _header.setText("Winner");
            _username.setText(_socket.getWinner());
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
        _socket.disconnect();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handlePlayAgain(final ActionEvent event) {
        _socket.restartRoom(_socket.getRoom().getString("code"));
    }
}