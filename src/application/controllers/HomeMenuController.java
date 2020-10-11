package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModelText;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * This class is the HomeMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class HomeMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

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
        if (GameModelText.getInstance().remainingQuestions()) {
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
        _sceneManager.switchScene(SceneManager.Scenes.LOGIN_SCREEN);
    }

    /**
     * Used to handle Leaderboard button
     *
     * @param event
     */
    @FXML
    private void handleLeaderboardButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.LEADERBOARD);
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
