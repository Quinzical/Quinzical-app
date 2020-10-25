package quinzical.controllers;

import quinzical.controllers.helper.StarBackground;
import quinzical.helper.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

/**
 * This class is the Help Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class HelpScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with HelpScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
    }

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
