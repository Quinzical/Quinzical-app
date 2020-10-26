package quinzical.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import quinzical.controllers.util.StarBackground;
import quinzical.util.JWTStore;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.socket.SocketIO;

/**
 * This class is the Chat Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ChatController {

    private static final String ENDPOINT = "https://quinzical-api.herokuapp.com";
    private static final String CHAT = "/chat";

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();

    private HttpClient _client = HttpClient.newHttpClient();

    @FXML
    private AnchorPane _anchor;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    @FXML
    private VBox _chatbox;

    @FXML
    private TextField _messageField;

    /**
     * initialize with ChatScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        Platform.runLater(() -> {
            _socket.setPane(_chatbox);
        });
    }

    /**
     * Used to handle send message button
     * 
     * @param event
     */
    @FXML
    private void handleSendMessage(final ActionEvent event) {
        if (_messageField.getText().trim() == "") {
            return;
        }
        new Thread(() -> {
            try {
                JSONObject json = new JSONObject();
                json.put("message", _messageField.getText().trim());
                _messageField.setText("");

                JWTStore jwtStore = new JWTStore();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ENDPOINT + CHAT))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + jwtStore.getJWT())
                        .POST(BodyPublishers.ofString(json.toString())).build();
                _client.send(request, BodyHandlers.ofString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Used to handle back button
     * 
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.backScene();
    }

    /**
     * Used to handle settings button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

}
