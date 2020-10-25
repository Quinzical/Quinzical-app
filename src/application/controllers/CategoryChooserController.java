package application.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.controllers.helper.CategoryButton;
import application.controllers.helper.LeaderboardAlert;
import application.controllers.helper.StarBackground;
import application.helper.SceneManager;
import application.models.helper.Category;
import application.models.login.LoginModel;
import application.models.practice.PracticeModel;
import application.models.practice.sql.PracticeModelSQL;
import application.models.sql.db.GameSessionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * This class is the Category Chooser controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class CategoryChooserController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final PracticeModel _practiceModel = PracticeModelSQL.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    private static final Insets TILEPANE_INSETS = new Insets(10, 10, 10, 10);

    private GameSessionDB _gameSessionDB = new GameSessionDB();

    private List<CategoryButton> _categories = new ArrayList<CategoryButton>();
    private List<CategoryButton> _selected = new ArrayList<CategoryButton>();

    @FXML
    private VBox _possibleCategoriesPane;

    @FXML
    private VBox _chosenCategoriesPane;

    @FXML
    private Button _submitBtn;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with CategoryChooser.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _submitBtn.setDisable(true);
        for (Category category : _practiceModel.getPracticeCategories()) {
            CategoryButton btn = new CategoryButton(category) {
                @Override
                public void onAction() {
                    if (_categories.contains(this)) {
                        selectCategory(this);
                    } else {
                        removeCategory(this);
                    }
                }
            };
            TilePane.setMargin(btn, TILEPANE_INSETS);
            _categories.add(btn);
        }
        _possibleCategoriesPane.getChildren().setAll(_categories);
    }

    /**
     * Used when selected a category
     * 
     * @param btn
     */
    private void selectCategory(final CategoryButton btn) {
        if (_selected.size() == 5) {
            return;
        }
        _categories.remove(btn);
        _selected.add(btn);
        _possibleCategoriesPane.getChildren().setAll(_categories);
        _chosenCategoriesPane.getChildren().setAll(_selected);

        if (_selected.size() == 5) {
            for (CategoryButton categoryBtn : _categories) {
                categoryBtn.setDisable(true);
            }
            _submitBtn.setDisable(false);
        }
    }

    /**
     * Used when deselecting a category
     * 
     * @param btn
     */
    private void removeCategory(final CategoryButton btn) {
        if (_selected.size() == 5) {
            for (CategoryButton categoryBtn : _categories) {
                categoryBtn.setDisable(false);
            }
            _submitBtn.setDisable(true);
        }
        _selected.remove(btn);
        _categories.add(btn);
        _possibleCategoriesPane.getChildren().setAll(_categories);
        _chosenCategoriesPane.getChildren().setAll(_selected);
    }

    /**
     * Used to handle back button
     *
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.backScene();
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
     * Used to handle setting button
     *
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle submit button
     *
     * @param event
     */
    @FXML
    private void handleSubmit(final ActionEvent event) {
        try {
            List<String> categories = new ArrayList<String>();
            for (CategoryButton category : _selected) {
                categories.add(String.valueOf(category.getCategory().getCategoryID()));
            }
            int id = _gameSessionDB.insert(_login.getUserID(), String.join(",", categories));
            _login.setGameSessionID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        _sceneManager.switchScene(SceneManager.Scenes.GAME_MENU);
    }
}
