package application.controllers;

import application.controllers.helper.ConfirmAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
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
	private void handleExitButton(ActionEvent event) {
        new ConfirmAlert("Quit the Game"){
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
    private void handlePlayButton(ActionEvent event) {
		if(GameModel.getInstance().remainingQuestions()) {
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
	private void handlePracticeButton(ActionEvent event) {
		_sceneManager.switchScene(Scenes.PRACTICE_MENU);
	}

	/**
	 * Used to handle setting button
	 * 
	 * @param event
	 */
	@FXML
    private void handleSettingsButton(ActionEvent event) {
		_sceneManager.switchScene(Scenes.SETTINGS_MENU);
	}

}
