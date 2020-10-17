package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.LeaderboardAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
import application.models.game.sql.GameModelSQL;
import application.models.login.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
    private Button _internationalButton;

    /**
     * initialize with LoginScreenController.fxml
     */
    public void initialize() {
        _usernameLabel.setText(_login.getUsername());

        // TODO disable only if not unlocked
        if (!_login.checkInternational()) {
            _internationalButton.setDisable(true);
        }
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
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.PRACTICE_MENU);
    }

    /**
     * Used to handle help button
     *
     * @param event
     */
    @FXML
    private void handleInternationalButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.INTERNATIONAL_QUESTION);
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
     * Used to handle logout button
     *
     * @param event
     */
    @FXML
    private void handleLogoutButton(final ActionEvent event) {
        _sceneManager.unloadScene();
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

    /**
     * Used to handle Leaderboard button
     *
     * @param event
     */
    @FXML
    private void handleLeaderboardButton(final ActionEvent event) {
        new LeaderboardAlert();
    }
}
