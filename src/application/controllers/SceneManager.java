package application.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneManager {

    // CONST
    private final static String PATH = "/application/resources/";
    public enum Scenes {
        HOME_MENU("HomeMenu.fxml"),
        PRACTICE_MENU("PracticeMenu.fxml");
        
        private final String file;

        Scenes(String file) {
            this.file = file;
        }
    }

    // Create Singleton
    private static final SceneManager instance = new SceneManager();

    private Stage _rootStage;
    private final Map<String, Scene> _scenes = new HashMap<String, Scene>();

    private SceneManager() {
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public void init(Stage rootStage) {
        if (rootStage == null) {
            throw new IllegalArgumentException();
        }
        
        rootStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent t) {
                // Save game?
            }
        });
        rootStage.setTitle("Quinzical");
        rootStage.getIcons().add(new Image(getPath("images/darklogo.png")));
        rootStage.setWidth(1280);
        rootStage.setHeight(800);
        rootStage.setMinWidth(800);
        rootStage.setMinHeight(600);
        rootStage.show();

        _rootStage = rootStage;
    }

    public void switchScene(Scenes name) {
        Scene scene = _scenes.computeIfAbsent(name.file, k -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(getPath(k)));
                return new Scene(root, 1280, 800);
            }catch(IOException ex){
                throw new RuntimeException(ex);
            }
        });
        _rootStage.setScene(scene);
    }

    private String getPath(String name) {
        return PATH + name;
    }
}