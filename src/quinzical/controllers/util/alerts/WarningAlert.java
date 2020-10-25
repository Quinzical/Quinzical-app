package quinzical.controllers.util.alerts;

/**
 * This class is for WarningAlert for Warning Message on Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class WarningAlert extends StyleAlert {
    /**
     * Create ConfirmAlert that displays a window to confirm.
     * 
     * @param text
     */
    public WarningAlert(final String text) {
        super(AlertType.WARNING);
        setHeaderText(text);
        show();
    }
}
