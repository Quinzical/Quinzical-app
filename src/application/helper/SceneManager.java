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
public final class SceneManager {

    // CONST
    private static final String PATH = FileHelper.FILE_SEPARATOR + "application" + FileHelper.FILE_SEPARATOR
            + "resources" + FileHelper.FILE_SEPARATOR;

    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 800;
    private static final int MINIMUM_WIDTH = 800;
    private static final int MINIMUM_HEIGHT = 700;

    /** Scene Logo path */
    public static final String LOGO = "images" + FileHelper.FILE_SEPARATOR + "darklogo.png";

    private static SceneManager _instance = new SceneManager();

    private Stage _rootStage;
    private Stack<Scenes> _history = new Stack<Scenes>();
    private final Map<Scenes, Scene> _scenes = new HashMap<Scenes, Scene>();

    /** Enum for each scene with a filename and name */
    public enum Scenes {
        /** Home Menu scene */
        HOME_MENU("HomeMenu.fxml"),
        /** Practice Menu scene */
        PRACTICE_MENU("PracticeMenu.fxml"),
        /** Settings Menu scene */
        SETTINGS_MENU("SettingsMenu.fxml"),
        /** Practice Module Question scene */
        PRACTICE_QUESTION("PracticeQuestion.fxml"),
        /** Game Module Question scene */
        GAME_QUESTION("GameQuestion.fxml"),
        /** Game Module Question scene */
        INTERNATIONAL_QUESTION("InternationalQuestion.fxml"),
        /** Game Menu scene */
        GAME_MENU("GameMenu.fxml"),
        /** Reward Screen scene */
        REWARD_SCREEN("RewardScreen.fxml"),
        /** Login Screen scene */
        LOGIN_SCREEN("LoginScreen.fxml"),
        /** Leaderboard scene */
        LEADERBOARD("LeaderboardScreen.fxml"),
        /** International Leaderboard scene */
        INTERNATIONAL_LEADERBOARD("InternationalLeaderboard.fxml"),
        /** Category chooser scene */
        CATEGORY_CHOOSER("CategoryChooser.fxml"),
        /** Help Screen scene */
        HELP_SCREEN("HelpScreen.fxml");

        private final String _filename;

        Scenes(final String filename) {
            _filename = filename;
        }
    }

    private SceneManager() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return SceneManager
     */
    public static SceneManager getInstance() {
        return _instance;
    }

    /**
     * Used to init rootStage for single instance of this class.
     * 
     * @param rootStage
     */
    public void init(final Stage rootStage) throws IllegalArgumentException {
        if (rootStage == null) {
            throw new IllegalArgumentException();
        }

        rootStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent t) {
                t.consume();
                new ConfirmAlert("Quit the Game") {
                    @Override
                    protected void handleConfirm() {
                        rootStage.close();
                        System.exit(0);
                    }
                };
            }
        });
        rootStage.setTitle("Quinzical");
        rootStage.getIcons().add(new Image(getPath(LOGO)));
        rootStage.setWidth(DEFAULT_WIDTH);
        rootStage.setHeight(DEFAULT_HEIGHT);
        rootStage.setMinWidth(MINIMUM_WIDTH);
        rootStage.setMinHeight(MINIMUM_HEIGHT);
        rootStage.show();
        _rootStage = rootStage;
    }

    /**
     * Used to switch current scene to new scene using Scenes enums.
     *
     * @param scene Scenes enum
     * @throws RuntimeException
     */
    public void switchScene(final Scenes scene) throws RuntimeException {
        Scene next = _scenes.computeIfAbsent(scene, k -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource(getPath(k._filename)));
                return new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
        System.exit(0);
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
     * @param name of the path
     * @return path
     */
    public static String getPath(final String name) {
        return (PATH + name).replace(FileHelper.FILE_SEPARATOR, "/");
    }
}
