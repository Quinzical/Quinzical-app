package application.controllers;

import application.controllers.helper.PracticeCategoryButton;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
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

    // TODO change to category object
    private final String[] categories = { "Place", "People", "Test", "Category" };

    @FXML
    private TilePane _categoriesPane;

    /**
     * initialize with PracticeMenu.fxml
     */
    public void initialize() {
        // Temporary add buttons
        for (String category : categories) {
            PracticeCategoryButton btn = new PracticeCategoryButton(category);
            _categoriesPane.getChildren().add(btn);
            TilePane.setMargin(btn, new Insets(10, 10, 10, 10));
        }
    }

    /**
     * Used to handle back button
     * 
     * @param event
     */
    @FXML
    void handleBackButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    /**
     * Used to handle settings button
     * 
     * @param event
     */
    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

}
