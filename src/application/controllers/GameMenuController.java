package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private GridPane _questionGrid;

    @FXML
    private Label _categoryLabel5;

    @FXML
    private Label _categoryLabel4;

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

}
