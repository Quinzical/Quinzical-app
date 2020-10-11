package application.controllers;

import application.helper.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class LeaderboardController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private VBox _leaderVBox;

    /**
     * initialize with LeaderboardScreen.fxml
     */
    public void initialize() {
        // TODO
    }

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
     * Used to handle settings button
     *
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.SETTINGS_MENU);
    }
}
