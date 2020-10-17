package application.controllers;

import java.util.List;

import application.controllers.helper.LeaderboardPosition;
import application.helper.SceneManager;
import application.models.api.LeaderboardEntry;
import application.models.api.LeaderboardModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    /**
     * initialize with LeaderboardScreen.fxml
     */
    public void initialize() {
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
        _sceneManager.unloadScene();
        _sceneManager.switchScene(SceneManager.Scenes.HOME_MENU);
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
