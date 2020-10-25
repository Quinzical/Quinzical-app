package quinzical.controllers.helper;

import quinzical.helper.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is for WarningAlert for Warning Message on Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class WarningAlert extends Alert {
    /**
     * Create ConfirmAlert that displays a window to confirm.
     * 
     * @param text
     */
    public WarningAlert(final String text) {
        super(AlertType.WARNING);
        setTitle("Qunizical");
        setHeaderText(text);
        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

        show();
    }
}
