package application.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.helper.Category;
import application.models.question.QuestionModel;
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

    private final QuestionModel _questionModel = QuestionModel.getInstance();

    // ExecutorService for running task and speak in the background
    private ExecutorService _team = Executors.newSingleThreadExecutor();
    private Speak _speak;
    private String _question;

    @FXML
    private Label _categoryName;

    @FXML
    private Label _questionLabel;

    @FXML
    private TextField _answerTextField;

    public void initialize(){
        _question = _questionModel.getQuestion();
        _questionLabel.setText(_question);
        _speak = new Speak(_question);
        _team.submit(_speak);
    }

    @FXML
    void handleDontKnowButton(ActionEvent event) {
        // TODO
        back();
    }

    @FXML
    void handlePlaybackButton(ActionEvent event) {
        // TODO
        _speak.cancel(true);
        _speak = new Speak(_question);
        _team.submit(_speak);
    }

    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
        // TODO needs more to work properly not sure how to do the rest
    	QuestionModel questionModel = QuestionModel.getInstance();
    	if (_sceneManager.getPreviousScene() == Scenes.PRACTICE_MENU) {
    		questionModel.setPractice(true);
    		questionModel.setCategory(new Category(_categoryName.getText()));
    	} else {
    		questionModel.setPractice(false);
    		questionModel.setCategory(new Category(_categoryName.getText()));
    	}
    	boolean correct = questionModel.checkAnswer(_answerTextField.getText());
       
    	back();
    }

    /**
     * back to scene
     */
    private void back() {
        _speak.cancel(true);
        _sceneManager.unloadScene();
        _sceneManager.backScene();
    }
}
