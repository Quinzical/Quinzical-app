package quinzical.controllers.online;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import quinzical.controllers.util.buttons.LobbyButton;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.api.Lobby;

/**
 * This class is used for viewing lobby and choosing which lobby to join
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LobbyMenuController {

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
    private TilePane _categoriesPane;

    /**
     * initialize with PracticeMenu.fxml
     */
    public void initialize() {
        Lobby lobby = new Lobby();
        Iterator<String> keys = lobby.getRooms();
        while (keys.hasNext()) {
            String key = keys.next();
            _categoriesPane.getChildren().add(new LobbyButton(key));
        }
    }

    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.cleanSwitchScene(Scenes.ONLINE_MENU);
    }

}
