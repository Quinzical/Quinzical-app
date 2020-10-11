
package application.processes;

import application.models.SettingsModel;

import javafx.concurrent.Task;

import java.io.IOException;

/**
 * Speak the input from the constructor. The task is run in the background using
 * a executorservice. The task can be cancelled.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class SpeakProcess extends Task<Void> {
    private ProcessBuilder _pb;

    /**
     * Construct process for espeak with input speak.
     * 
     * @param speak
     */
    public SpeakProcess(final String speak) {
        int speed = SettingsModel.getEspeakSpeed();
        int volume = SettingsModel.getEspeakVolume();
        _pb = new ProcessBuilder("espeak", "-s", String.valueOf(speed), "-a", String.valueOf(volume), speak);
    }

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws IOException {
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
