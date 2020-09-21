package application.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SceneManager {
    /**
     * This class manages javafx stage and scene. This class caches all scenes and
     * stores history of scenes.
     * 
     * @author Maggie Pedersen
     * @author Cheng-Zhen Yang
     */

    // CONST
    private final static String PATH = "/application/resources/";

    public enum Scenes {
        HOME_MENU("HomeMenu.fxml"), PRACTICE_MENU("PracticeMenu.fxml");

        private final String file;

        Scenes(String file) {
            this.file = file;
        }
    }

    private static final SceneManager instance = new SceneManager();

    private Stage _rootStage;
    private Stack<String> _history = new Stack<String>();
    private final Map<String, Scene> _scenes = new HashMap<String, Scene>();

    private SceneManager() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return SceneManager
     */
    public static SceneManager getInstance() {
        return instance;
    }

    /**
     * Used to init rootStage for single instance of this class.
     * 
     * @return SceneManager
     */
    public void init(Stage rootStage) throws IllegalArgumentException {
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

    /**
     * Used to switch current scene to new scene using Scenes enums.
     *
     * @param name Scenes enum
     */
    public void switchScene(Scenes name) {
        Scene scene = _scenes.computeIfAbsent(name.file, k -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(getPath(k)));
                return new Scene(root, 1280, 800);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        _rootStage.setScene(scene);
    }

    /**
     * Used to return root path
     * 
     * @param name
     * @return
     */
    private String getPath(String name) {
        return PATH + name;
    }
}