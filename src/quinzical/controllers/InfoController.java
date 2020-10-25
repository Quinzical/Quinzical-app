package quinzical.controllers;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * This class is the Info Scene controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class InfoController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with Info.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
    }

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
