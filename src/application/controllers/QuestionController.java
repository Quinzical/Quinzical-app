package application.controllers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.question.QuestionModel;
import application.processes.SpeakProcess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private SpeakProcess _speak;
    private String _question;

    private int _attempt;

    @FXML
    private Label _categoryName;

    @FXML
    private Label _questionLabel;

    @FXML
    private TextField _answerTextField;

    @FXML
    private Button _dontKnowButton;

    @FXML
    private Button _submitButton;

    @FXML
    private Label _infoLabel;

    /**
     * Used to initialize QuestionController and speak question
     */
    public void initialize() {
        _question = _questionModel.getQuestion();
        if (_questionModel.getPractice()) {
            _questionLabel.setText(_question);
        } else {
            _questionLabel.setText("");
        }
        _attempt = 0;
        _speak = new SpeakProcess(_question);
        _infoLabel.setText("");
        _team.submit(_speak);
        _answerTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            _infoLabel.setText("");
        });
    }

    /**
     * Used to cancel previous speak and speak new text
     * 
     * @param text
     */
    private void speak(String text) {
        _speak.cancel(true);
        _speak = new SpeakProcess(text);
        _team.submit(_speak);
    }

    /**
     * Used to initalise Back button to be shown after completing attempts
     */
    private void createBackButton() {
        _dontKnowButton.setDisable(true);
        _submitButton.setText("Back");
        _submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _speak.cancel(true);
                back();
            }
        });
    }

    /**
     * Used to go back to scene
     */
    private void back() {
        _sceneManager.unloadScene();
        _sceneManager.backScene();
    }

    /**
     * Used to handle Don't Know Button different handler for practice mode and
     * 
     * @param event
     */
    @FXML
    void handleDontKnowButton(ActionEvent event) {
        if (_questionModel.getPractice()) {
            _attempt = 2;
            _questionModel.setNumberOfAttempts(_attempt);
            speak("The first letter is " + _questionModel.getClue());
            _infoLabel.setText("Clue");
            _infoLabel.setStyle("-fx-text-fill: green;");
            _dontKnowButton.disableProperty();
        } else {
            speak("The answer is " + _questionModel.getCorrectAnswer());
            _infoLabel.setText("Correct");
            _infoLabel.setStyle("-fx-text-fill: green;");
            createBackButton();
        }
    }

    /**
     * Used to handle Playback Button to play the question again
     * 
     * @param event
     */
    @FXML
    void handlePlaybackButton(ActionEvent event) {
        speak(_question);
    }

    /**
     * Used to handle Settings Button to change scene to settings
     * 
     * @param event
     */
    @FXML
    void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle submit button to submit answer and check answers
     * 
     * @param event
     */
    @FXML
    void handleSubmitButton(ActionEvent event) {
        String oldAnswer = _answerTextField.getText();
        String correctAnswer = _questionModel.getCorrectAnswer();
        boolean correct = _questionModel.checkAnswer(oldAnswer);

        if (correct) {
            speak(correctAnswer + " is Correct");
            _infoLabel.setText("Correct");
            _infoLabel.setStyle("-fx-text-fill: green;");
            createBackButton();
        } else if (_questionModel.getPractice()) {
            // Handle practice mode with multiple attempts
            if (_attempt > 2) {
                // MNore than 3 attempts
                speak(oldAnswer + " is Incorrect. The answer is " + correctAnswer);
                createBackButton();
            } else if (_attempt == 2) {
                // On the 3rd attempt
                String clue = _questionModel.getClue();
                speak(oldAnswer + " is Incorrect. The first letter is " + clue);
                _answerTextField.setText(clue);
            } else {
                speak(oldAnswer + " is Incorrect.");
                _answerTextField.setText("");
            }
            _infoLabel.setText("Incorrect");
            _infoLabel.setStyle("-fx-text-fill: red;");
            // Increment attempt and set turns
            _attempt += 1;
            _questionModel.setNumberOfAttempts(_attempt);
        } else {
            speak(oldAnswer + " is Incorrect. The answer is " + correctAnswer);
            createBackButton();
        }
    }
}
