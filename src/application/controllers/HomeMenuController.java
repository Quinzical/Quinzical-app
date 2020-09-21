package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeMenuController {
    /**
     * This class is the HomeMenu controller in a MVC design.
     * 
     * @author Maggie Pedersen
     * @author Cheng-Zhen Yang
     */

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * Used to handle exit button
     * 
     * @param event
     */
    @FXML
    void handleExitButton(ActionEvent event) {
        _sceneManager.close();
    }

    /**
     * Used to handle play button
     * 
     * @param event
     */
    @FXML
    void handlePlayButton(ActionEvent event) {
        // TODO
    }

    /**
     * Used to handle practice button
     * 
     * @param event
     */
    @FXML
    void handlePracticeButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.PRACTICE_MENU);
    }

    /**
     * Used to handle setting button
     * 
     * @param event
     */
    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

}
