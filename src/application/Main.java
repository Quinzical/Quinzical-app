package application;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.init(primaryStage);
        sceneManager.switchScene(Scenes.HOME_MENU);
    }

    public static void main(final String[] args) {
        launch();
    }
}
