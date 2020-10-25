
package quinzical.util.processes;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import quinzical.util.SceneManager;

/**
 * This process is designed to wait for server to start on heroku.
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */

public class APILoader extends Task<Void> {
    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final int OK = 200;

    private int _statusCode;

    private HttpClient _client = HttpClient.newHttpClient();

    /**
     * Construct process for loading heroku to cold boot
     */
    public APILoader() {
    }

    /**
     * Run in process in background.
     */
    @Override
    protected Void call() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT)).GET().build();
        HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

        _statusCode = response.statusCode();
        return null;
    }

    /**
     * Done
     */
    @Override
    protected void succeeded() {
        if (_statusCode != OK) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Quinzical");
            alert.setHeaderText("Server Connection has failed");
            // Add Icon
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

            alert.showAndWait();
            System.exit(0);
        }
    }
}
