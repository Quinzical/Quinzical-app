package application.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
import application.models.game.sql.GameModelSQL;
import application.processes.SpeakProcess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class is the Reward Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class RewardScreenController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final GameModel _gameModel = GameModelSQL.getInstance();

    // ExecutorService for running task and speak in the background
    private ExecutorService _team = Executors.newSingleThreadExecutor();
    private SpeakProcess _speak;

    @FXML
    private Label _userScore;

    @FXML
    private Button _menuButton;

    @FXML
    private Button _playAgainButton;

    /**
     * Used to initialize RewardScreenController.
     */
    public void initialize() {
        int score = _gameModel.getScore();
        _userScore.setText("$" + Integer.toString(score));
        _speak = new SpeakProcess(
                "Congratulations, you completed the Game Module! Your final score was "
                + Integer.toString(score));
        _team.submit(_speak);
    }

    /**
     * Used to handle main menu button.
     * 
     * @param event
     */
    @FXML
    private void handleMenuButton(final ActionEvent event) {
        _speak.cancel(true);
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    /**
     * Used to handle play again menu button.
     * 
     * @param event
     */
    @FXML
    private void handlePlayAgainButton(final ActionEvent event) {
        _speak.cancel(true);
        _sceneManager.unloadScene();
        _gameModel.resetGameModule();
        _sceneManager.switchScene(Scenes.GAME_MENU);
    }

    /**
     * Used to handle setting button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _speak.cancel(true);
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }
}
