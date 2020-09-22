package application.controllers;

import application.controllers.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is the Question controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Label _categoryName;

    @FXML
    private Label _questionLabel;

    @FXML
    private TextField _answerTextField;

    @FXML
    void handleDontKnowButton(ActionEvent event) {
        // TODO
        _sceneManager.backScene();
    }

    @FXML
    void handlePlaybackButton(ActionEvent event) {
        // TODO
    }

    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
        // TODO
        _sceneManager.backScene();
    }

}
