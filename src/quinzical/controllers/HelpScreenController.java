package quinzical.controllers;

import quinzical.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * This class is the Help Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class HelpScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * Used to handle back button
     *
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.backScene();
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
