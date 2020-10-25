package application.controllers;

import java.util.List;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.GameCategoryButton;
import application.controllers.helper.GameStateData;
import application.controllers.helper.LeaderboardAlert;
import application.controllers.helper.StarBackground;
import application.controllers.helper.SuccessAlert;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.game.GameModel;
import application.models.game.sql.GameModelSQL;
import application.models.helper.Category;
import application.models.helper.SplashModel;
import application.models.helper.SplashModel.Pages;
import application.models.login.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private final LoginModel _login = LoginModel.getInstance();

    private static final int COLUMNS = 5;
    private static final int ROWS = 6;
    private static final int SCORE_INCREMENT = 100;

    private List<Category> _categories;

    @FXML
    private Button _currentScore;

    @FXML
    private Button _internationalButton;

    @FXML
    private GridPane _questionGrid;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with GameMenu.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _categories = _gameModel.getGameCategories();
        GameStateData state = _gameModel.getGameStateData();
        _currentScore.setText("$" + _gameModel.getScore());

        for (int i = 0; i < COLUMNS; i++) {
            Category category = _categories.get(i);
            Label categoryLabel = new Label(category.toString());
            categoryLabel.setStyle("-fx-font-size:24; -fx-fill: white;");
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

        if (!_login.checkInternational() && _gameModel.checkInternational()) {
            new SuccessAlert("International Section", "International section has been unlocked");
            _login.enableInternational();
        }

        if (!_login.checkInternational()) {
            _internationalButton.setDisable(true);
        }
    }

    /**
     * Used to handle back to main menu button
     * 
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.unloadScene(Scenes.HOME_MENU);
        _sceneManager.switchScene(Scenes.HOME_MENU);
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
        new LeaderboardAlert();
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

    /**
     * Used to handle help button
     *
     * @param event
     */
    @FXML
    private void handleInternationalButton(final ActionEvent event) {
        if (_gameModel.getScore() < 0) {
            new ConfirmAlert("You must have a score 0 or higher to attempt an international question.") {
                @Override
                protected void handleConfirm() {
                }
            };
            return;
        }
        SplashModel.getInstance().setNextScene(Scenes.INTERNATIONAL_QUESTION, Pages.INTERNATIONAL);
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.SPLASH_SCREEN);
    }
}
