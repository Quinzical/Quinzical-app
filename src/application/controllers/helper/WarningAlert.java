package application.controllers.helper;

import application.helper.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
