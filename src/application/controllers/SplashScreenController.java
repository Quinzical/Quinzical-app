package application.controllers;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.sql.SQLConnection;
import javafx.application.Platform;

/**
 * This class is the SplashScreen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SplashScreenController {
    /**
     * initialize with SplashScreen.fxml
     */
    public void initialize() {
        SQLConnection.getInstance();
        Platform.runLater(() -> {
            SceneManager.getInstance().switchScene(Scenes.LOGIN_SCREEN);
        });
    }
}
