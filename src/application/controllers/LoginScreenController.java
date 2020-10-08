package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * This class is the LoginScreen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LoginScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private TextField _usernameField;

    @FXML
    private void handleLoginButton() {
        // TODO
    }

    @FXML
    private void handleRegisterButton() {
        // TODO
    }

    /**
     * Used to handle setting button
     */
    @FXML
    private void handleSettingsButton() {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle exit button
     */
    @FXML
    private void handleExitButton() {
        new ConfirmAlert("Quit the Game") {
            @Override
            protected void handleConfirm() {
                _sceneManager.close();
            }
        };
    }
}
