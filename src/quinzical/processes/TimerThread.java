package application.processes;

import application.helper.QuestionTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This class is used to run a timer for the game module on a different thread
 * so that the user can see the Question Screen while listening to the TTS.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class TimerThread extends Thread {

    private SpeakProcess _speak;

    private Label _timerLabel;
    private Button _submitButton;
    private Button _dontKnowButton;
    private TextField _answerTextField;

    private QuestionTimer _questionTimer;

    /**
     * Create TimerThread with question pages
     * 
     * @param speak
     * @param timerLabel
     * @param dontKnow
     * @param submit
     * @param answer
     */
    public TimerThread(final SpeakProcess speak, final Label timerLabel, final Button dontKnow, final Button submit, final TextField answer) {
        _speak = speak;
        _timerLabel = timerLabel;
        _dontKnowButton = dontKnow;
        _submitButton = submit;
        _answerTextField = answer;
    }

    /**
     * Override run method.
     */
    @Override
    public final void run() {
        _questionTimer = new QuestionTimer(_timerLabel, _submitButton, _speak);
        _questionTimer.setBinding();
        _questionTimer.startCountdown();

        QuestionUpdater updater = new QuestionUpdater(_submitButton, _dontKnowButton, _answerTextField);
        Platform.runLater(updater);
    }

    /**
     * Used to stop the countdown timer from running and interrupt the helper thread it is running on.
     */
    public final void stopThread() {
        _questionTimer.stopCountdown();
        interrupt();
    }
}
