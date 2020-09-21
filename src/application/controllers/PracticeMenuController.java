package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public class PracticeMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private TilePane _categoriesPane;

    @FXML
    void handleBackButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    @FXML
    void handleSettingsButton(ActionEvent event) {
        // TODO
    }

}
