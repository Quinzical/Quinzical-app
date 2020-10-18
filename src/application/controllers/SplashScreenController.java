package application.controllers;

import application.helper.SceneManager;
import application.models.helper.SplashModel;
import javafx.application.Platform;

/**
 * This class is the SplashScreen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SplashScreenController {

    private SplashModel _splashModel = SplashModel.getInstance();

    /**
     * initialize with SplashScreen.fxml
     */
    public void initialize() {
        _splashModel.doMethod();
        Platform.runLater(() -> {
            SceneManager.getInstance().switchScene(_splashModel.getNextScene());
        });
    }
}
