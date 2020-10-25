package quinzical.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;

/**
 * This class is the Chat Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ChatController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private AnchorPane _anchor;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    @FXML
    private ScrollPane _messageScrollPane;

    @FXML
    private TextField _messageField;

    /**
     * initialize with ChatScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
    }

    /**
     * Used to handle send message button
     * 
     * @param event
     */
    @FXML
    private void handleSendMessage(final ActionEvent event) {
        // TODO
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
