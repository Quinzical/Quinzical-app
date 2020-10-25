package quinzical.controllers;

//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
// import quinzical.processes.SpeakProcess;

import quinzical.helper.SceneManager;
import quinzical.helper.SceneManager.Scenes;
import quinzical.models.game.GameModel;
import quinzical.models.game.sql.GameModelSQL;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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
    //private ExecutorService _team = Executors.newSingleThreadExecutor();

    @FXML
    private Label _userScore;

    @FXML
    private Button _menuButton;

    @FXML
    private Button _playAgainButton;

    @FXML
    private ImageView _backgroundFade;

    /**
     * Used to initialize RewardScreenController.
     */
    public void initialize() {
        FadeTransition logo = new FadeTransition(Duration.seconds(5), _backgroundFade);
        logo.setFromValue(0);
        logo.setToValue(1);
        logo.setAutoReverse(true);
        logo.play();
    }

    /**
     * Used to handle main menu button.
     * 
     * @param event
     */
    @FXML
    private void handleMenuButton(final ActionEvent event) {
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
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }
}
