package quinzical.controllers.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.game.GameModel;
import quinzical.util.models.game.GameModelSQL;
import quinzical.util.models.QuestionModel;
import quinzical.util.processes.SpeakProcess;
import quinzical.util.processes.TimerThread;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * This class is the Game Module Question controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameQuestionController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private final QuestionModel _questionModel = QuestionModel.getInstance();

    private final GameModel _gameModel = GameModelSQL.getInstance();

    // ExecutorService for running task and speak in the background
    private ExecutorService _team = Executors.newSingleThreadExecutor();
    private SpeakProcess _speak;
    private String _question;

    private int _caretPosition;

    private TimerThread _timer;

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

    @FXML
    private Label _timerLabel;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * Used to initialize QuestionController and speak question
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _question = _questionModel.getQuestion();
        if (_questionModel.getPractice()) {
            _questionLabel.setText(_question);
        } else {
            _questionLabel.setText("");
        }

        _dontKnowButton.setDisable(true);
        _submitButton.setDisable(true);
        _answerTextField.setEditable(false);

        _speak = new SpeakProcess(_question);
        _categoryName.setText(_questionModel.getCategory().toString());
        _infoLabel.setText("");
        _team.submit(_speak);

        _timer = new TimerThread(_speak, _timerLabel, _dontKnowButton, _submitButton, _answerTextField);
        _timer.start();
        _answerTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    handleSubmitButton(new ActionEvent());
                }
            }
        });
        _answerTextField.setPromptText(_questionModel.getQuestionPrompt() + "...");
    }

    /**
     * Used to cancel previous speak and speak new text
     * 
     * @param text
     */
    private void speak(final String text) {
        _speak.cancel(true);
        _speak = new SpeakProcess(text);
        _team.submit(_speak);
    }

    /**
     * Used to disable TextField Enter
     */
    private void disableEnter() {
        _answerTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent keyEvent) {
                // Do Nothing
            }
        });
    }

    /**
     * Used to initalise Back button to be shown after completing attempts
     */
    private void createBackButton() {
        disableEnter();
        _dontKnowButton.setDisable(true);
        _submitButton.setText("Back");
        _submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
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
        _team.shutdown();
        if (_gameModel.remainingQuestions()) {
            _sceneManager.backScene();
        } else {
            _sceneManager.switchScene(Scenes.REWARD_SCREEN);
        }
    }

    /**
     * Used to handle Don't Know Button different handler for practice mode and
     * 
     * @param event
     */
    @FXML
    private void handleDontKnowButton(final ActionEvent event) {
        _timer.stopThread();
        if (!_questionModel.getPractice()) {
            String correctAnswer = _questionModel.getCorrectAnswer();
            speak("The answer is " + correctAnswer);
            _infoLabel.setText("The correct answer was:");
            _infoLabel.setStyle("-fx-text-fill: green;");
            _answerTextField.setText(correctAnswer);
            _gameModel.deleteQuestion(_questionModel.getQuestionValue());
            createBackButton();
        }
    }

    /**
     * Used to handle Playback Button to play the question again
     * 
     * @param event
     */
    @FXML
    private void handlePlaybackButton(final ActionEvent event) {
        if (!_timer.isAlive()) {
            speak(_question);
        }
    }

    /**
     * Used to handle Settings Button to change scene to settings
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle submit button to submit answer and check answers
     * 
     * @param event
     */
    @FXML
    private void handleSubmitButton(final ActionEvent event) {
        _timer.stopThread();
        String oldAnswer = _answerTextField.getText();
        String correctAnswer = _questionModel.getCorrectAnswer();
        boolean correct = _questionModel.checkAnswer(oldAnswer);

        if (correct) {
            speak(correctAnswer + " is Correct");
            _infoLabel.setText("Correct");
            _infoLabel.setStyle("-fx-text-fill: green;");
            createBackButton();
        } else {
            _infoLabel.setText("Incorrect");
            _infoLabel.setStyle("-fx-text-fill: red;");
            speak(oldAnswer + " is Incorrect. The answer is " + correctAnswer);
            _answerTextField.setText("Answer: " + correctAnswer);
            createBackButton();
        }
    }

    /**
     * Used to update caret postion when text is entered to the answer text field
     * 
     * @param event
     */
    @FXML
    private void handleTextInput(final KeyEvent event) {
        _caretPosition = _answerTextField.getCaretPosition();
    }

    /**
     * Used to update caret postion when the text field is clicked at a different position
     * 
     * @param event
     */
    @FXML
    private void handleTextMouse(final MouseEvent event) {
        _caretPosition = _answerTextField.getCaretPosition();
    }

    /**
     * Used to add ā macron to the text field
     * 
     * @param event
     */
    @FXML
    private void handleA(final ActionEvent event) {
        insertMacron("ā");
    }

    /**
     * Used to add ē macron to the text field
     * 
     * @param event
     */
    @FXML
    private void handleE(final ActionEvent event) {
        insertMacron("ē");
    }

    /**
     * Used to add ī macron to the text field
     * 
     * @param event
     */
    @FXML
    private void handleI(final ActionEvent event) {
        insertMacron("ī");
    }

    /**
     * Used to add ō macron to the text field
     * 
     * @param event
     */
    @FXML
    private void handleO(final ActionEvent event) {
        insertMacron("ō");
    }

    /**
     * Used to add ū macron to the text field
     * 
     * @param event
     */
    @FXML
    private void handleU(final ActionEvent event) {
        insertMacron("ū");
    }

    /**
     * Used to insert a macron into the text field at the caret position.
     * 
     * @param macron the macron to be added
     * @return
     */
    private void insertMacron(final String macron) {
        _answerTextField.insertText(_caretPosition, macron);
        KeyEvent press = new KeyEvent(KeyEvent.KEY_PRESSED, macron, macron, KeyCode.UNDEFINED, false, false, false, false);
        _answerTextField.fireEvent(press);
    }
}
