package application.controllers;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
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
    	GameModel gameModel = GameModel.getInstance();
    	int score = gameModel.getScore();
    	_userScore.setText("$" + Integer.toString(score));
    }
    
    /**
     * Used to handle main menu button.
     * 
     * @param event
     */
    @FXML
    void handleMenuButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }
    
    /**
     * Used to handle play again menu button.
     * 
     * @param event
     */
    @FXML
    void handlePlayAgainButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.GAME_MENU); 
    }

}
