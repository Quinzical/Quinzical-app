package quinzical.controllers.local;

import java.util.List;
import quinzical.controllers.util.buttons.PracticeCategoryButton;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.util.Category;
import quinzical.util.models.practice.PracticeModel;
import quinzical.util.models.practice.PracticeModelSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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

    /**
     * initialize with PracticeMenu.fxml
     */
    public void initialize() {
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
