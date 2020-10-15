package application.controllers;

import java.util.List;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.GameCategoryButton;
import application.controllers.helper.GameStateData;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
import application.models.game.sql.GameModelSQL;
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
    private final GameModel _gameModel = GameModelSQL.getInstance();

    private static final int COLUMNS = 5;
    private static final int ROWS = 6;
    private static final int SCORE_INCREMENT = 100;

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
        GameStateData state = _gameModel.getGameStateData();

        _currentScore.setText("$" + _gameModel.getScore());

        for (int i = 0; i < COLUMNS; i++) {
            Category category = _categories.get(i);
            Label categoryLabel = new Label(category.toString());
            categoryLabel.setStyle("-fx-font-size:24");
            GridPane.setHalignment(categoryLabel, HPos.CENTER);
            _questionGrid.add(categoryLabel, i, 0);
            int questionNum = _gameModel.getCategoriesQuestionNumber(category);

            for (int j = 1; j < ROWS; j++) {
                int btnState = -1;
                if (questionNum < ROWS - j) {
                    btnState = state.getCategoryState(i)[j - 1];
                }
                GameCategoryButton btn = new GameCategoryButton(_categories.get(i), String.valueOf(j * SCORE_INCREMENT),
                        btnState);
                if (questionNum != ROWS - j) {
                    btn.setDisable(true);
                }
                GridPane.setHalignment(btn, HPos.CENTER);
                _questionGrid.add(btn, i, j);
            }
        }
    }

    /**
     * Used to handle back to main menu button
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
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle game reset button
     * 
     * @param event
     */
    @FXML
    private void handleResetButton(final ActionEvent event) {
        new ConfirmAlert("Reset the Game") {
            @Override
            protected void handleConfirm() {
                _gameModel.resetGameModule();
                _questionGrid.getChildren().clear();
                initialize();
            }
        };
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
     * Used to handle Help button
     *
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }
}
