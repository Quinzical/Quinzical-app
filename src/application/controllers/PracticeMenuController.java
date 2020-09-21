package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public class PracticeMenuController {
    /**
     * This class is the PracticeMenu controller in a MVC design.
     * 
     * @author Maggie Pedersen
     * @author Cheng-Zhen Yang
     */

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private TilePane _categoriesPane;

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
        // TODO
    }

}
