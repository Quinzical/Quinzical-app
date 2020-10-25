package quinzical.controllers;

import java.sql.SQLException;

import quinzical.controllers.helper.ConfirmAlert;
import quinzical.controllers.helper.ExceptionAlert;
import quinzical.controllers.helper.StarBackground;
import quinzical.controllers.helper.SuccessAlert;
import quinzical.controllers.helper.WarningAlert;
import quinzical.helper.SceneManager;
import quinzical.helper.SceneManager.Scenes;
import quinzical.models.api.Login;
import quinzical.models.api.LoginEntry;
import quinzical.models.helper.JWTStore;
import quinzical.models.login.LoginModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


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
    private TextField _passwordField;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with LoginScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    handleLoginButton(new ActionEvent());
                }
            }
        });
    }

    @FXML
    private void handleLoginButton(final ActionEvent event) {
        if (_usernameField.getText().isEmpty()) {
            new WarningAlert("Please enter a username.");
            return;
        } else if (_passwordField.getText().isEmpty()) {
            new WarningAlert("Please enter a password.");
            return;
        }

        String username = _usernameField.getText().trim();
        String password = _passwordField.getText().trim();

        LoginEntry entry = globalLogin(username, password);
        if (entry == null) {
            return;
        }
        _loginModel.setMongoID(entry.getUserID());

        if (entry.getUserID() != "") {

            boolean userExists = localLogin(username);
            if (!userExists) {
                localRegister(username);
                localLogin(username);
            }
            JWTStore jwtStore = new JWTStore();
            jwtStore.setJWT(entry.getJwtToken());
            _sceneManager.unloadScene();
            _sceneManager.switchScene(Scenes.OPENING_MENU);
        }
    }

    @FXML
    private void handleRegisterButton(final ActionEvent event) {
        if (_usernameField.getText().isEmpty()) {
            new WarningAlert("Please enter a username.");
            return;
        }

        String username = _usernameField.getText().trim();
        String password = _passwordField.getText().trim();

        LoginEntry entry = globalRegister(username, password);
        if (entry == null) {
            return;
        }
        _loginModel.setMongoID(entry.getUserID());

        if (entry.getUserID() != "") {
            JWTStore jwtStore = new JWTStore();
            jwtStore.setJWT(entry.getJwtToken());
            localRegister(username);
            new SuccessAlert("The username " + username + " has been successfully registered!", "You may now login.");
        }
    }

    /**
     * Used to handle setting button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle exit button
     * 
     * @param event
     */
    @FXML
    private void handleExitButton(final ActionEvent event) {
        new ConfirmAlert("Quit the Game") {
            @Override
            protected void handleConfirm() {
                _sceneManager.close();
            }
        };
    }


    /**
     * Used to handle offline button
     * 
     * @param event
     */
    @FXML
    private void handleOfflineButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.OFFLINE);
    }

    /**
     * Used to login to local DB
     * 
     * @param username
     * @return boolean
     */
    public boolean localLogin(final String username) {
        boolean userExists = false;

        try {
            userExists = _loginModel.loginUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }
        return userExists;
    }

    /**
     * Used to register to local DB
     * 
     * @param username
     * @return boolean
     */
    public boolean localRegister(final String username) {
        boolean userExists = false;
        try {
            userExists = _loginModel.checkUserExists(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }

        if (userExists) {
            new WarningAlert("This username is already registered.");
            return false;
        }

        try {
            _loginModel.registerUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
            new ExceptionAlert(e);
        }
        return true;
    }

    /**
     * Used to login globally on Quinzical api
     * 
     * @param username
     * @param password
     * @return String id
     */
    private LoginEntry globalLogin(final String username, final String password) {
        Login login = new Login();
        return login.postLogin(username, password);
    }

    /**
     * Used to register globally on Quinzical api
     * 
     * @param username
     * @param password
     * @return String id
     */
    private LoginEntry globalRegister(final String username, final String password) {
        Login login = new Login();
        return login.postRegister(username, password);
    }
}
