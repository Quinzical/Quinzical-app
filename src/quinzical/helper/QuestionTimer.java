package application.helper;

import application.processes.SpeakProcess;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * This class is used to create a countdown timer of specified length.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionTimer {

    private static final Integer START_TIME = 30;
    private Timeline _timeline;
    private IntegerProperty _time = new SimpleIntegerProperty(START_TIME);

    private Label _timerLabel;
    private Button _submitButton;

    private SpeakProcess _speak;

    /**
     * Create Question Timer helper
     * 
     * @param timerLabel
     * @param submit
     * @param speak
     */
    public QuestionTimer(final Label timerLabel, final Button submit, final SpeakProcess speak) {
        _timerLabel = timerLabel;
        _submitButton = submit;
        _speak = speak;
    }

    /**
     * Used to bind updates in the time value to the label displayed to the user.
     */
    public final void setBinding() {
        _timerLabel.textProperty().bind(_time.asString());
    }

    /**
     * Used to start a countdown for a specified amount of time.
     */
    public void startCountdown() {
        if (_timeline != null) {
            _timeline.stop();
        }
        _time.set(START_TIME);
        _timeline = new Timeline();
        _timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(START_TIME + 1), new KeyValue(_time, 0)));
        while (!_speak.isDone()) {
            // Blocking. Wait till speak process is finished.
        }
        _timeline.playFromStart();
        _timeline.setOnFinished(event -> _submitButton.fireEvent(new ActionEvent()));
    }

    /**
     * Used to stop the current running countdown.
     */
    public void stopCountdown() {
        _timeline.stop();
    }
}
