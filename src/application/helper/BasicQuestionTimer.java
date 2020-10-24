package application.helper;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * This class is used to create a countdown timer of specified length.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class BasicQuestionTimer {

    private Timeline _timeline;
    private IntegerProperty _time;

    private int _timer;
    private Label _timerLabel;

    /**
     * Create Question Timer helper
     * 
     * @param timerLabel
     * @param timer
     */
    public BasicQuestionTimer(final Label timerLabel, final int timer) {
        _timerLabel = timerLabel;
        _time = new SimpleIntegerProperty(timer);
        _timer = timer;
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
        _time.set(_timer);
        _timeline = new Timeline();
        _timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(_timer + 1), new KeyValue(_time, 0)));
        _timeline.playFromStart();
    }

    /**
     * Used to stop the current running countdown.
     */
    public void stopCountdown() {
        _timeline.stop();
    }
}
