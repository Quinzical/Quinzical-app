package application.processes;

import application.helper.BasicQuestionTimer;
import javafx.scene.control.Label;

/**
 * This class is only used to do a basic count down
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class BasicTimerThread extends Thread {

    private Label _timerLabel;
    private int _timer;

    private BasicQuestionTimer _questionTimer;

    /**
     * Create TimerThread with question pages
     * 
     * @param timer
     * @param timerLabel
     */
    public BasicTimerThread(final Label timerLabel, final int timer) {
        _timerLabel = timerLabel;
        _timer = timer;
    }

    /**
     * Override run method.
     */
    @Override
    public final void run() {
        _questionTimer = new BasicQuestionTimer(_timerLabel, _timer);
        _questionTimer.setBinding();
        _questionTimer.startCountdown();
    }

    /**
     * Used to stop the countdown timer from running and interrupt the helper thread
     * it is running on.
     */
    public final void stopThread() {
        _questionTimer.stopCountdown();
        interrupt();
    }
}
