package quinzical.controllers.util.alerts;

import java.util.Optional;

import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.api.LeaderboardModel;
import quinzical.util.models.SplashModel;
import quinzical.util.models.SplashModel.Pages;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * This class is for LeaderboardAlert for choosing which leaderboard to view
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LeaderboardAlert extends StyleAlert {

    private SceneManager _sceneManager = SceneManager.getInstance();
    private LeaderboardModel _leaderboard = LeaderboardModel.getInstance();

    /**
     * Create an alert to view local or global
     */
    public LeaderboardAlert() {
        super(AlertType.CONFIRMATION);
        setTitle("Leaderboard choice");
        setHeaderText("Would you like to view the local or global leaderboard?");
        setContentText("Choose your option:");

        ButtonType buttonTypeLocal = new ButtonType("Local");
        ButtonType buttonTypeGlobal = new ButtonType("Global");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        getButtonTypes().setAll(buttonTypeLocal, buttonTypeGlobal, buttonTypeCancel);
        Optional<ButtonType> result = showAndWait();

        if (result.isPresent()) {
            ButtonType choice = result.get();
            if (choice == buttonTypeLocal) {
                _leaderboard.setGlobal(false);
            } else if (choice == buttonTypeGlobal) {
                _leaderboard.setGlobal(true);
            } else {
                return;
            }
            SplashModel.getInstance().setNextScene(Scenes.LEADERBOARD, Pages.LEADERBOARD);
            _sceneManager.switchScene(SceneManager.Scenes.SPLASH_SCREEN);
        }
    }
}
