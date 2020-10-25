package quinzical.controllers.util.alerts;

import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 * This class is for ConfirmAlert for Confirm Button on Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public abstract class ConfirmAlert extends StyleAlert {
    /**
     * Create ConfirmAlert that displays a window to confirm.
     * 
     * @param text
     */
    public ConfirmAlert(final String text) {
        super(AlertType.CONFIRMATION);
        setHeaderText(text);
        setContentText("Are you ok with this?");

        // Show Alert
        Optional<ButtonType> result = showAndWait();
        if (result.get() == ButtonType.OK) {
            handleConfirm();
        }
    }

    /**
     * Runs when Alert is confirmed
     */
    protected abstract void handleConfirm();
}
