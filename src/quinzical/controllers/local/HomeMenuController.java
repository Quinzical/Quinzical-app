package quinzical.controllers.local;

import quinzical.controllers.util.alerts.ConfirmAlert;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.game.GameModel;
import quinzical.util.models.game.GameModelSQL;
import quinzical.util.models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * This class is the HomeMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class HomeMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();
    private GameModel _gameModel = GameModelSQL.getInstance();

    @FXML
    private Label _usernameLabel;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with HomeMenu.fxml
     */
    public void initialize() {
        _usernameLabel.setText(_login.getUsername());
        _usernameLabel.getStyleClass().add("logingreen");
        StarBackground.animate(_background1, _background2, _background3);
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
     * Used to handle play button
     * 
     * @param event
     */
    @FXML
    private void handlePlayButton(final ActionEvent event) {
        if (_login.getGameSessionID() == 0) {
            _sceneManager.switchScene(Scenes.CATEGORY_CHOOSER);
        } else if (_gameModel.remainingQuestions()) {
            _sceneManager.switchScene(Scenes.GAME_MENU);
        } else {
            _sceneManager.switchScene(Scenes.REWARD_SCREEN);
        }
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
     * Used to handle logout button
     *
     * @param event
     */
    @FXML
    private void handleLogoutButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.OPENING_MENU);
    }

    /**
     * Used to handle help button
     *
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.HELP_SCREEN);
    }

    /**
     * Used to handle stats button
     *
     * @param event
     */
    @FXML
    private void handleStatsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.STATS_SCREEN);
    }

     /**
     * Used to handle info button
     * 
     * @param event
     */
    @FXML
    private void handleInfoButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.INFO);
    }
}
