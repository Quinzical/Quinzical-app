package application.controllers;

import application.helper.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HelpScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * Used to handle back button
     *
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HOME_MENU);
    }

    /**
     * Used to handle setting button
     *
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.SETTINGS_MENU);
    }
}
