package application.controllers.helper;

import java.util.Optional;

import application.helper.SceneManager;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public abstract class ConfirmAlert extends Alert {
    /**
     * Create ConfirmAlert that displays a window to confirm.
     * 
     * @param text
     */
    public ConfirmAlert(final String text) {
        super(AlertType.CONFIRMATION);
        setTitle("Qunizical");
        setHeaderText(text);
        setContentText("Are you ok with this?");
        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

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
