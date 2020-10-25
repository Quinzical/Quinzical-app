package quinzical.controllers;

import java.util.List;

import quinzical.controllers.helper.LeaderboardPosition;
import quinzical.controllers.helper.StarBackground;
import quinzical.helper.SceneManager;
import quinzical.helper.SceneManager.Scenes;
import quinzical.models.api.LeaderboardEntry;
import quinzical.models.api.LeaderboardModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * This class is the Leaderboard Controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LeaderboardController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private final LeaderboardModel _leaderboard = LeaderboardModel.getInstance();

    private static final Double DEFAULT_SPACING = 10.0;

    @FXML
    private VBox _leaderVBox;

    @FXML
    private Label _headerText;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with LeaderboardScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _leaderVBox.setSpacing(DEFAULT_SPACING);
        _headerText.setText(_leaderboard.getHeader());
        List<LeaderboardEntry> leaders = _leaderboard.getLeaderboard();

        int position = 1;
        for (LeaderboardEntry entry : leaders) {
            LeaderboardPosition boardPosition = new LeaderboardPosition(entry, position);
            position++;
            _leaderVBox.getChildren().add(boardPosition.getComponent());
        }
    }

    /**
     * Used to handle back button
     *
     * @param event
     */
    @FXML
    private void handleBackButton(final ActionEvent event) {
        _sceneManager.unloadScene(Scenes.LEADERBOARD);
        _sceneManager.backSceneTwice();
    }

    /**
     * Used to handle settings button
     *
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.SETTINGS_MENU);
    }
}
