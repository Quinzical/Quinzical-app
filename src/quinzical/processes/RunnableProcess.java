
package quinzical.processes;

import javafx.concurrent.Task;
import java.io.IOException;

/**
 * This process is designed to load DB.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class RunnableProcess extends Task<Void> {
    private Runnable _load;

    /**
     * Construct process for loading SQL database
     * 
     * @param load
     */
    public RunnableProcess(final Runnable load) {
        _load = load;
    }

    /**
     * Run in process in background with cancelled option.
     */
    @Override
    protected Void call() throws IOException {
        _load.run();
        return null;
    }
}
