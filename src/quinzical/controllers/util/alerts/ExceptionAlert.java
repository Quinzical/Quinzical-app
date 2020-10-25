package quinzical.controllers.util.alerts;

/**
 * This class is for ExceptionAlert for Exceptions on Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ExceptionAlert extends StyleAlert {
    /**
     * Create ExceptionAlert that displays a error alert with an exception
     * 
     * @param exception
     */
    public ExceptionAlert(final Exception exception) {
        super(AlertType.ERROR);
        setHeaderText("An Exception has occured");
        setContentText(exception.toString());

        show();
    }
}
