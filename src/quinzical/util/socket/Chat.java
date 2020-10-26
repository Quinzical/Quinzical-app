package quinzical.util.socket;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Chat classed used to handle all socket chat
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class Chat {

    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String CHAT = "/chat";

    private HttpClient _client = HttpClient.newHttpClient();
    private VBox _pane;

    /**
     * Used to setup Chat object for getting messages
     * 
     * @param socket
     */
    public Chat(final Socket socket) {
        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                JSONObject message = (JSONObject) args[0];
                Platform.runLater(() -> {
                    JSONObject item = message.getJSONObject("message");
                    String msg = item.getJSONObject("user").getString("username") + ": " + item.getString("message");
                    Label label = new Label(msg);
                    label.setStyle("-fx-font-size:20;");
                    label.setWrapText(true);
                    _pane.getChildren().add(label);
                });
            }
        });
    }

    /**
     * Used to set message scroll pane
     * 
     * @param pane
     */
    public void setPane(final VBox pane) {
        _pane = pane;
    }

    /**
     * Used to get history of chat
     *
     */
    public void history() {
        new Thread(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + CHAT)).GET().build();
                HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

                JSONArray array = new JSONArray(response.body());

                for (int i = array.length() - 1; i >= 0; i--) {
                    JSONObject item = array.getJSONObject(i);
                    final String msg = item.getJSONObject("user").getString("username") + ": "
                            + item.getString("message");

                    Platform.runLater(() -> {
                        Label label = new Label(msg);
                        label.setStyle("-fx-font-size:20;");
                        label.setWrapText(true);
                        _pane.getChildren().add(label);
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
