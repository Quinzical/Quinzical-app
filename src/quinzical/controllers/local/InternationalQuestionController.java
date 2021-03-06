package quinzical.controllers.local;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quinzical.controllers.util.alerts.LeaderboardAlert;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.game.GameModel;
import quinzical.util.models.game.InternationalModel;
import quinzical.util.models.game.GameModelSQL;
import quinzical.util.models.SplashModel;
import quinzical.util.models.SplashModel.Pages;
import quinzical.util.models.LoginModel;
import quinzical.util.processes.SpeakProcess;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class is the InternationalQuestion.fxml controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class InternationalQuestionController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private final InternationalModel _internationalModel = InternationalModel.getInstance();
    private final GameModel _gameModel = GameModelSQL.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    // ExecutorService for running task and speak in the background
    private ExecutorService _team = Executors.newSingleThreadExecutor();
    private SpeakProcess _speak;

    @FXML
    private Label _categoryName;

    @FXML
    private Label _questionLabel;

    @FXML
    private TextField _answerTextField;

    @FXML
    private Label _infoLabel;

    @FXML
    private Button _dontKnowButton;

    @FXML
    private Button _submitButton;

    @FXML
    private Button _currentScore;

    /**
     * Used to initialize InternalQuestionController and speak question
     */
    public void initialize() {
        _currentScore.setText("$" + Integer.toString(_gameModel.getScore()));
        String question = _internationalModel.getInternationalQuestion();

        _questionLabel.setText(question);

        _speak = new SpeakProcess(question);
        _categoryName.setText(_internationalModel.getInternationalCategory());
        _infoLabel.setText("Worth $" + Integer.toString(_internationalModel.getInternationalValue()));
        _team.submit(_speak);
        _answerTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    handleSubmitButton(new ActionEvent());
                }
            }
        });
        _answerTextField.setPromptText("Answer here...");
    }

    /**
     * Used to handle setting button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle Leaderboard button
     *
     * @param event
     */
    @FXML
    private void handleLeaderboardButton(final ActionEvent event) {
        new LeaderboardAlert();
    }

    /**
     * Used to handle Playback Button to play the question again
     * 
     * @param event
     */
    @FXML
    private void handlePlaybackButton(final ActionEvent event) {
        speak(_internationalModel.getInternationalQuestion());
    }

    /**
     * Used to handle Settings Button to change scene to settings
     * 
     * @param event
     */
    @FXML
    void handleSubmitButton(final ActionEvent event) {
        String oldAnswer = _answerTextField.getText();
        String correctAnswer = _internationalModel.getInternationalAnswer();
        boolean correct = _internationalModel.checkInternationalAnswer(oldAnswer);

        if (correct) {
            speak(correctAnswer + " is Correct");
            _infoLabel.setText("Correct");
            _infoLabel.setStyle("-fx-text-fill: green;");
            _internationalModel.addInternationalScore(_login.getGameSessionID(), _internationalModel.getInternationalValue());
            _currentScore.setText("$" + Integer.toString(_gameModel.getScore()));
        } else {
            speak(oldAnswer + " is Incorrect. The answer s " + correctAnswer);
            _infoLabel.setText("Incorrect");
            _infoLabel.setStyle("-fx-text-fill: red;");
            _internationalModel.addInternationalScore(_login.getGameSessionID(), -_internationalModel.getInternationalValue());
            if (_gameModel.getScore() < _internationalModel.getInternationalValue()) {
                _submitButton.setDisable(true);
            }
            _currentScore.setText("$" + Integer.toString(_gameModel.getScore()));
            _answerTextField.setText("Answer: " + correctAnswer);
        }
        createNextQuestionButton();
        createBackButton();
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
     * Used to initalise Back button to be shown after completing attempts
     */
    private void createBackButton() {
        disableEnter();
        _dontKnowButton.setText("Back");
        _dontKnowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                _speak.cancel(true);
                back();
            }
        });
    }

    /**
     * Used to initalise Back button to be shown after completing attempts
     */
    private void createNextQuestionButton() {
        disableEnter();
        _submitButton.setText("Next Question");
        _submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                _speak.cancel(true);
                reload();
            }
        });
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
     * Used to handle Don't Know Button different handler for practice mode and
     * 
     * @param event
     */
    @FXML
    private void handleDontKnowButton(final ActionEvent event) {
        String correctAnswer = _internationalModel.getInternationalAnswer();
        speak("The answer is " + correctAnswer);
        _infoLabel.setText("The correct answer was:");
        _infoLabel.setStyle("-fx-text-fill: green;");
        _answerTextField.setText(correctAnswer);
        createBackButton();
        createNextQuestionButton();
    }

    /**
     * Used to go back to scene
     */
    private void back() {
        _sceneManager.unloadScene();
        _team.shutdown();
        _sceneManager.switchScene(Scenes.GAME_MENU);
    }

    /**
     * Used to go to the next question
     */
    private void reload() {
        SplashModel.getInstance().setNextScene(Scenes.INTERNATIONAL_QUESTION, Pages.INTERNATIONAL);
        _sceneManager.unloadScene();
        _team.shutdown();
        _sceneManager.switchScene(Scenes.SPLASH_SCREEN);
    }
}
