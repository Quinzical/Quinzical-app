package application.controllers;

import java.sql.SQLException;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.ExceptionAlert;
import application.controllers.helper.SuccessAlert;
import application.controllers.helper.WarningAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.login.LoginModel;
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
    private final LoginModel _loginModel = LoginModel.getInstance();

    @FXML
    private TextField _usernameField;

    @FXML
    private void handleLoginButton() {
        if (_usernameField.getText().isEmpty()) {
            new WarningAlert("Please enter a username.");
            return;
        }

        String username = _usernameField.getText().trim();
        boolean userExists = false;
        
        try {
            userExists = _loginModel.loginUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }

        if (!userExists) {
            new WarningAlert("Invalid username. Register it or try again.");
            return;
        }

        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    @FXML
    private void handleRegisterButton() {
        if (_usernameField.getText().isEmpty()) {
            new WarningAlert("Please enter a username.");
            return;
        }

        String username = _usernameField.getText().trim();

        boolean userExists = false;
        try {
            userExists = _loginModel.checkUserExists(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }

        if (userExists) {
            new WarningAlert("This username is already registered.");
            return;
        }

        try {
            _loginModel.registerUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }

        new SuccessAlert("The username " + username + " has been successfully registered!", "You may now login.");
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
