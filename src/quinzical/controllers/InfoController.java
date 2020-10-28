package quinzical.controllers;

import quinzical.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * This class is the Info Scene controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class InfoController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * Used to handle back to original scene before info
     * 
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.backScene();
    }
}
