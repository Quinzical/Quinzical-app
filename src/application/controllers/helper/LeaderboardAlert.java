package application.controllers.helper;

import java.util.Optional;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.api.LeaderboardModel;
import application.models.helper.SplashModel;
import application.models.helper.SplashModel.Pages;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This class is for LeaderboardAlert for choosing which leaderboard to view
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LeaderboardAlert extends Alert {

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

        // Add Icon
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(SceneManager.getPath(SceneManager.LOGO)));

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
