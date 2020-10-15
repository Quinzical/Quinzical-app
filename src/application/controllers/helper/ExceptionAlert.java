package application.controllers.helper;

import application.helper.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is for ExceptionAlert for Exceptions on Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ExceptionAlert extends Alert {
    /**
     * Create ExceptionAlert that displays a error alert with an exception
     * 
     * @param exception
     */
    public ExceptionAlert(final Exception exception) {
        super(AlertType.ERROR);
        setTitle("Qunizical");
        setHeaderText("An Exception has occured");
        setContentText(exception.toString());
        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

        show();
    }
}
