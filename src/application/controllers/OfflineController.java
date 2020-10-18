package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This class is the Offline Menu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OfflineController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Button _playButton;

    /**
     * initialize with LoginScreenController.fxml
     */
    public void initialize() {
        _playButton.setDisable(true);
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
     * Used to handle practice button
     * 
     * @param event
     */
    @FXML
    private void handlePracticeButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.PRACTICE_MENU);
    }

    /**
     * Used to handle setting button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle login button
     *
     * @param event
     */
    @FXML
    private void handleLoginButton(final ActionEvent event) {
        _sceneManager.unloadAllScenes();
        _sceneManager.switchScene(SceneManager.Scenes.LOGIN_SCREEN);
    }

    /**
     * Used to handle help button
     *
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }
}
