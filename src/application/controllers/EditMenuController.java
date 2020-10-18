package application.controllers;

import java.util.List;

import application.controllers.helper.EditCategoryButton;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.helper.Category;
import application.models.practice.PracticeModel;
import application.models.practice.sql.PracticeModelSQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.TilePane;

/**
 * This class is the EditMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class EditMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final PracticeModel _practiceModel = PracticeModelSQL.getInstance();

    private static final Insets TILEPANE_INSETS = new Insets(10, 10, 10, 10);

    private List<Category> _categories;

    @FXML
    private TilePane _categoriesPane;

    /**
     * initialize with EditMenu.fxml
     */
    public void initialize() {
        _practiceModel.setUpPracticeModule();
        _categories = _practiceModel.getPracticeCategories();
        for (Category category : _categories) {
            EditCategoryButton btn = new EditCategoryButton(category);
            _categoriesPane.getChildren().add(btn);
            TilePane.setMargin(btn, TILEPANE_INSETS);
        }
        EditCategoryButton btn = new EditCategoryButton();
        _categoriesPane.getChildren().add(btn);
        TilePane.setMargin(btn, TILEPANE_INSETS);
    }

    /**
     * Used to handle back button
     * 
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
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