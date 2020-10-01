package application.controllers;

import java.util.List;

import application.controllers.helper.GameCategoryButton;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
import application.models.helper.Category;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class is the GameMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameMenuController {

	private final SceneManager _sceneManager = SceneManager.getInstance();
	private final GameModel _gameModel = GameModel.getInstance();

	private List<Category> _categories;

	@FXML
	private Button _currentScore;

	@FXML
	private GridPane _questionGrid;

	/**
	 * initialize with GameMenu.fxml
	 */
	public void initialize() {
		_gameModel.setUpGameModule();
		_categories = _gameModel.getGameCategories();

		_currentScore.setText("$"+_gameModel.getScore());

		if(_gameModel.remainingQuestions()) {
			for (int i = 0; i < 5; i++) {
				Category category = _categories.get(i);
				Label categoryLabel = new Label(category.toString());
				categoryLabel.setStyle("-fx-font-size:24");
				GridPane.setHalignment(categoryLabel, HPos.CENTER);
				_questionGrid.add(categoryLabel, i, 0);
				int questionNum = _gameModel.getCategoriesQuestionNumber(category);

				for (int j = 1; j < 6; j++) {
					GameCategoryButton btn = new GameCategoryButton(_categories.get(i), String.valueOf(j * 100));
					if (questionNum != 6-j) {
						btn.setDisable(true);
					}
					GridPane.setHalignment(btn, HPos.CENTER);
					_questionGrid.add(btn, i, j);
				}
			}
		} else {
			_sceneManager.unloadScene();
			_sceneManager.switchScene(Scenes.REWARD_SCREEN);
		}
	}

	@FXML
	void handleBackButton(ActionEvent event) {
		// TODO
		_sceneManager.backScene();
	}

	@FXML
	void handleSettingsButton(ActionEvent event) {
		// TODO
		_sceneManager.switchScene(Scenes.SETTINGS_MENU);
	}

	@FXML 
	void handleResetButton(ActionEvent event){
		//TODO

	}

}
