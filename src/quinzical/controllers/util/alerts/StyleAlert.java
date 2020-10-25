package quinzical.controllers.util.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import quinzical.util.SceneManager;

/**
 * This class is used to style all Alerts
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public abstract class StyleAlert extends Alert {
    /**
     * Create a styled alert with style sheet and logo
     * @param alertType
     */
    public StyleAlert(final AlertType alertType) {
        super(alertType);
        setTitle("Qunizical");
        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

        // Style alert
        DialogPane dialogPane = getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/quinzical/resources/quinzical.css").toExternalForm());
    }
}
