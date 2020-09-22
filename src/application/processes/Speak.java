
package application.processes;

import javafx.concurrent.Task;

/**
 * Speak the input from the constructor. The task is run in the background using
 * a executorservice. The task can be cancelled.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class Speak extends Task<Void> {
    private ProcessBuilder _pb;

    // TODO these two value should be fetched by model

    // Sets the speed in words-per-minute (approximate values for the default
    // English voice, others may differ slightly). The default value is 175. I
    // generally use a faster speed of 260. The lower limit is 80. There is no upper
    // limit, but about 500 is probably a practical maximum.
    private int _speed = 175;
    // Sets amplitude (volume) in a range of 0 to 200. The default is 100.
    private int _volume = 100;

    /**
     * Construct process for espeak with input speak.
     * 
     * @param speak
     */
    public Speak(String speak) {
        _pb = new ProcessBuilder("espeak", speak, "-s", String.valueOf(_speed), "-a", String.valueOf(_volume));
    }

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws Exception {
        Process process = _pb.start();

        // if cancelled, destroy process
        while (process.isAlive()) {
            if (isCancelled()) {
                process.destroy();
            }
        }
        return null;
    }
}
