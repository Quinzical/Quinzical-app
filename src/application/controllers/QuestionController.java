package application.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import application.controllers.SceneManager.Scenes;
import application.processes.Speak;
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

    // ExecutorService for running task and speak in the background
    private ExecutorService _team = Executors.newSingleThreadExecutor();
    private Speak _speak;

    @FXML
    private Label _categoryName;

    @FXML
    private Label _questionLabel;

    @FXML
    private TextField _answerTextField;

    public void initialize(){
        _speak = new Speak("This person lead New Zealand 2020");
        _team.submit(_speak);
    }

    @FXML
    void handleDontKnowButton(ActionEvent event) {
        // TODO
        _speak.cancel(true);
        _sceneManager.backScene();
    }

    @FXML
    void handlePlaybackButton(ActionEvent event) {
        // TODO
        _speak.cancel(true);
        _speak = new Speak("This person lead New Zealand 2020");
        _team.submit(_speak);
    }

    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
        // TODO
        _speak.cancel(true);
        _sceneManager.backScene();
    }

}
