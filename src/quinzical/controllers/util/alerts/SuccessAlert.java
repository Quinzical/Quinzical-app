package quinzical.controllers.util.alerts;
/**
 * This class is for SuccessAlert for Custom Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SuccessAlert extends StyleAlert {
    /**
     * Create Success Alert
     * @param header
     * @param content
     */
    public SuccessAlert(final String header, final String content) {
        super(AlertType.INFORMATION);
        setHeaderText(header);
        setContentText(content);
        show();
    }
}
