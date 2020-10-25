package quinzical.controllers.helper;

import quinzical.helper.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is for SuccessAlert for Custom Alert
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SuccessAlert extends Alert {
    /**
     * Create Success Alert
     * @param header
     * @param content
     */
    public SuccessAlert(final String header, final String content) {
        super(AlertType.INFORMATION);
        setTitle("Qunizical");
        setHeaderText(header);
        setContentText(content);
        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

        show();
    }
}
