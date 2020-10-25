package quinzical.processes;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This class is used to update GUI components after the initial speak process
 * has been run.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionUpdater implements Runnable {

    private Button _submitButton;
    private Button _dontKnowButton;
    private TextField _answerTextField;

    /**
     * Create Question Updater process
     * 
     * @param submit
     * @param dontKnow
     * @param answer
     */
    public QuestionUpdater(final Button submit, final Button dontKnow, final TextField answer) {
        _submitButton = submit;
        _dontKnowButton = dontKnow;
        _answerTextField = answer;
    }

    /**
     * Overrides Runnable run method to carry out nessecary tasks.
     */
    @Override
    public final void run() {
        _submitButton.setDisable(false);
        _dontKnowButton.setDisable(false);
        _answerTextField.setEditable(true);
    }
}
