package quinzical;

import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.sql.SQLConnection;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is tha Main class for Quinzical
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class MainStats extends Application {

    /**
     * Used to start the application with Login Screen
     * 
     * @param primaryStage stage
     */
    @Override
    public final void start(final Stage primaryStage) throws Exception {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.init(primaryStage);
        sceneManager.switchScene(Scenes.STATS_SCREEN);
        //StarsBackground.Stars(primaryStage);
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
