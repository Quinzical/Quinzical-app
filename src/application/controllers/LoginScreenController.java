package application.controllers;

import java.sql.SQLException;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.ExceptionAlert;
import application.controllers.helper.WarningAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.login.LoginModel;
import application.models.sql.data.UserData;
import application.models.sql.db.GameSessionDB;
import application.models.sql.db.UserDB;
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
    // TODO loginModel to login using sql
    private final LoginModel _loginModel = LoginModel.getInstance();
    private GameSessionDB _gameSessionDB = new GameSessionDB();

    @FXML
    private TextField _usernameField;

    /**
     * initialize with LoginScreenController.fxml
     */
    public void initialize() {
        try {
            // TODO temporary reseting to category 1,2,3,4,5
            int id = _gameSessionDB.insert(1, "1,2,3,4,5");
            _loginModel.setUser("test", 1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleLoginButton() {

        if (!_usernameField.getText().isEmpty()) {
            String username = _usernameField.getText();
            UserDB userDB = new UserDB();
            UserData user = null;
            try {
                user = userDB.loginUser(username);
            } catch (SQLException e) {
                e.printStackTrace();
                new ExceptionAlert(e);
            }
            if (user != null) {
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
            String userName = _usernameField.getText();
            // sql querey
        } else {
            new WarningAlert("Please enter a username.");
        }
        _sceneManager.switchScene(Scenes.HOME_MENU);
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
