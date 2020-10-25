package quinzical.controllers.local;

import quinzical.controllers.util.alerts.ConfirmAlert;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

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

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with Offline.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
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
        _sceneManager.switchScene(Scenes.PRACTICE_MENU);
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
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }
}
