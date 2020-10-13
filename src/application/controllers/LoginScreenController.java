package application.controllers;

import java.sql.SQLException;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.ExceptionAlert;
import application.controllers.helper.WarningAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.login.LoginModel;
import application.models.sql.db.GameSessionDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is the LoginScreen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LoginScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _loginModel = LoginModel.getInstance();

    private GameSessionDB _gameSessionDB = new GameSessionDB();

    @FXML
    private TextField _usernameField;

    @FXML
    private void handleLoginButton() {

        if (!_usernameField.getText().isEmpty()) {
            String username = _usernameField.getText().trim();

            boolean userExists = false;
            try {
                userExists = _loginModel.loginUser(username);
            } catch (SQLException e) {
                new ExceptionAlert(e);
            }

            if (userExists) {
                _sceneManager.switchScene(Scenes.HOME_MENU);
            } else {
                new WarningAlert("Invalid username. Register it or try again.");
            }

        } else {
            new WarningAlert("Please enter a username.");
        }
    }

    @FXML
    private void handleRegisterButton() {
        if (!_usernameField.getText().isEmpty()) {
            String username = _usernameField.getText().trim();

            boolean userExists = false;
            try {
                userExists = _loginModel.checkUserExists(username);
            } catch (SQLException e) {
                new ExceptionAlert(e);
            }

            if (!userExists) {
                try {
                    _loginModel.registerUser(username);
                } catch (SQLException e) {
                    new ExceptionAlert(e);
                }
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("The username " + username + " has been successfully registered!");
                alert.setContentText("You may now login.");
                alert.showAndWait();
            } else {
                new WarningAlert("This username is already registered.");
            }
        } else {
            new WarningAlert("Please enter a username.");
        }
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
