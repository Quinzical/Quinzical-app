package application;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.sql.SQLConnection;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is tha Main class for Quinzical
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class Main extends Application {

    /**
     * Used to start the application with Login Screen
     * 
     * @param primaryStage stage
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.init(primaryStage);
        sceneManager.switchScene(Scenes.SPLASH_SCREEN);
    }

    /**
     * Main entry point
     * 
     * @param args arguments for main
     */
    public static void main(final String[] args) {
        SQLConnection.getInstance();
        launch();
    }
}
