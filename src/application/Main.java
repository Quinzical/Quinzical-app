package application;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Used to start the application
     * 
     * @param primaryStage stage
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.init(primaryStage);
        sceneManager.switchScene(Scenes.HOME_MENU);
    }

    /**
     * Main entry point
     * 
     * @param args arguments for main
     */
    public static void main(final String[] args) {
        launch();
    }
}
