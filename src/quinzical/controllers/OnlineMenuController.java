package application.controllers;

import application.controllers.helper.StarBackground;
import application.controllers.helper.WarningAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.login.LoginModel;
import application.models.socket.SocketIO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with OnlineMenu.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
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

        _socket.joinRoom(_enterCode.getText().toLowerCase());
    }

    @FXML
    private void findGame(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CREATE_GAME);
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _sceneManager.backScene();
    }

}
