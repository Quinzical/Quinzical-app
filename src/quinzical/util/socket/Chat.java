package quinzical.util.socket;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;
import quinzical.util.JWTStore;

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
                    //TODO
                });
            }
        });
    }

    /**
     * Used to send a message using chat
     * 
     * @param message
     */
    public void send(final String message) {
        try {
            JSONObject json = new JSONObject();
            json.put("message", message);

            JWTStore jwtStore = new JWTStore();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + CHAT))
                    .header("Content-Type", "application/json").header("Authorization", "Bearer " + jwtStore.getJWT())
                    .POST(BodyPublishers.ofString(json.toString())).build();
            _client.send(request, BodyHandlers.ofString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
