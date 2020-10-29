package quinzical.controllers.online;

import quinzical.controllers.util.alerts.WarningAlert;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.LoginModel;
import quinzical.util.socket.SocketIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is used for the online page menu.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OnlineMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();

    private static final int CODE_LENGTH = 6;

    @FXML
    private Label _username;

    @FXML
    private TextField _enterCode;

    /**
     * initialize with OnlineMenu.fxml
     */
    public void initialize() {
        _username.setText(_login.getUsername());
        _username.getStyleClass().add("logingreen");
        _socket.setUsername(_login.getUsername());
    }

    @FXML
    private void createGame(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CREATE_GAME);
    }

    @FXML
    private void enterCode(final ActionEvent event) {
        if (_enterCode.getText().length() != CODE_LENGTH) {
            new WarningAlert("Lobby code is invalid");
            return;
        }

        _socket.joinRoom(_enterCode.getText().toUpperCase().trim());
    }

    @FXML
    private void findGame(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.LOBBY_MENU);
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.OPENING_MENU);
    }

}
