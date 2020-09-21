package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    void handleExitButton(ActionEvent event) {
        // TODO
    }

    @FXML
    void handlePlayButton(ActionEvent event) {
        // TODO
    }

    @FXML
    void handlePracticeButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.PRACTICE_MENU);
    }

    @FXML
    void handleSettingsButton(ActionEvent event) {
        // TODO 
    }

}
