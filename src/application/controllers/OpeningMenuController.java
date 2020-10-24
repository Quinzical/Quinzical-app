package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.helper.JWTStore;
import application.models.login.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class is the OpeningMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OpeningMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    @FXML
    private Label _usernameLabel;

    /**
     * initialize with OpeningMenu.fxml
     */
    public void initialize() {
        _usernameLabel.setText(_login.getUsername());
        _usernameLabel.getStyleClass().add("logingreen");
    }

    @FXML
    private void handleCartButton(final ActionEvent event) {

    }

    /**
     * Used to handle customise button
     * 
     * @param event
     */
    @FXML
    private void handleCustomiseButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CUSTOMISE_MENU);
    }

    /**
     * Used to handle help button
     * 
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }

    @FXML
    private void handleInfoButton(final ActionEvent event) {

    }

    @FXML
    private void handleLocalButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    @FXML
    private void handleMessageButton(final ActionEvent event) {

    }

    @FXML
    private void handleOnlineButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    @FXML
    private void handleStatsButton(final ActionEvent event) {

    }

    @FXML
    private void handleExit(final ActionEvent event) {
        new ConfirmAlert("Quit the Game") {
            @Override
            protected void handleConfirm() {
                _sceneManager.close();
            }
        };
    }

    @FXML
    private void handleLogout(final ActionEvent event) {
        _sceneManager.unloadAllScenes();
        _sceneManager.switchScene(Scenes.LOGIN_SCREEN);
        JWTStore jwtStore = new JWTStore();
        jwtStore.setJWT("");
    }
}
