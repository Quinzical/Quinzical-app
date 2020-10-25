package quinzical.controllers;

import java.util.List;
import quinzical.controllers.helper.PracticeCategoryButton;
import quinzical.controllers.helper.StarBackground;
import quinzical.helper.SceneManager;
import quinzical.helper.SceneManager.Scenes;
import quinzical.models.helper.Category;
import quinzical.models.practice.PracticeModel;
import quinzical.models.practice.sql.PracticeModelSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

/**
 * This class is the PracticeMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class PracticeMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final PracticeModel _practiceModel = PracticeModelSQL.getInstance();

    private static final Insets TILEPANE_INSETS = new Insets(10, 10, 10, 10);

    private List<Category> _categories;

    @FXML
    private TilePane _categoriesPane;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with PracticeMenu.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _practiceModel.setUpPracticeModule();
        _categories = _practiceModel.getPracticeCategories();
        for (Category category : _categories) {
            PracticeCategoryButton btn = new PracticeCategoryButton(category);
            _categoriesPane.getChildren().add(btn);
            TilePane.setMargin(btn, TILEPANE_INSETS);
        }
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
     * Used to handle settings button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

}
