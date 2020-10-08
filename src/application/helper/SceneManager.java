package application.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import application.controllers.helper.ConfirmAlert;
import application.controllers.helper.ExceptionAlert;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This class manages javafx stage and scene. This class caches all scenes and
 * stores history of scenes.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SceneManager {

    // CONST
    private final static String PATH = FileHelper.FILE_SEPARATOR + "application" + FileHelper.FILE_SEPARATOR
            + "resources" + FileHelper.FILE_SEPARATOR;

    public enum Scenes {
        HOME_MENU("HomeMenu.fxml"), PRACTICE_MENU("PracticeMenu.fxml"), SETTINGS_MENU("SettingsMenu.fxml"),
        QUESTION("Question.fxml"), GAME_MENU("GameMenu.fxml"), REWARD_SCREEN("RewardScreen.fxml"), LOGIN_SCREEN("LoginScreen.fxml"),
        LEADERBOARD("LeaderboardScreen.fxml"), CATEGORY_CHOOSER("CategoryChooser.fxml"), HELP_SCREEN("HelpScreen.fxml");

        private final String filename;

        Scenes(String filename) {
            this.filename = filename;
        }
    }

    private static final SceneManager instance = new SceneManager();

    private Stage _rootStage;
    private Stack<Scenes> _history = new Stack<Scenes>();
    private final Map<Scenes, Scene> _scenes = new HashMap<Scenes, Scene>();

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
            	new ConfirmAlert("Quit the Game") {
                    @Override
                    protected void handleConfirm() {
                        rootStage.close();
                    }
                };
            }
        });
        rootStage.setTitle("Quinzical");
        rootStage.getIcons().add(new Image(getPath("images" + FileHelper.FILE_SEPARATOR + "darklogo.png")));
        rootStage.setWidth(1280);
        rootStage.setHeight(800);
        rootStage.setMinWidth(800);
        rootStage.setMinHeight(700);
        rootStage.show();
        _rootStage = rootStage;
    }

    /**
     * Used to switch current scene to new scene using Scenes enums.
     *
     * @param name Scenes enum
     * @throws RuntimeException
     */
    public void switchScene(Scenes scene) throws RuntimeException {
        Scene next = _scenes.computeIfAbsent(scene, k -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(getPath(k.filename)));
                return new Scene(root, 1280, 800);
            } catch (IOException ex) {
                new ExceptionAlert(ex);
                throw new RuntimeException(ex);
            }
        });
        _history.push(scene);
        _rootStage.setScene(next);
    }

    /**
     * Used to switch back scene by popping current scene from the history
     */
    public void backScene() {
        _history.pop();
        switchScene(_history.pop());
    }

    /**
     * Used to unload scene
     */
    public void unloadScene() {
        _scenes.remove(_history.peek());
    }

    /**
     * Used to close stage/window
     */
    public void close() {
        _rootStage.close();
    }

    /**
     * Used to return the previous scene
     * 
     * @return Scenes the last scene displayed to the user
     */
    public Scenes getPreviousScene() {
        return _history.peek();
    }

    /**
     * Used to return static root path for javafx files
     * 
     * @param name
     * @return
     */
    public static String getPath(String name) {
        return (PATH + name).replace(FileHelper.FILE_SEPARATOR, "/");
    }
}