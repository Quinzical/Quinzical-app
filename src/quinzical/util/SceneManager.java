package quinzical.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import quinzical.controllers.util.alerts.ConfirmAlert;
import quinzical.controllers.util.alerts.ExceptionAlert;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
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
    private static final String FOLDER = "quinzical";
    private static final String PATH = FileHelper.FILE_SEPARATOR + FOLDER + FileHelper.FILE_SEPARATOR + "resources"
            + FileHelper.FILE_SEPARATOR;

    private static final int DEFAULT_WIDTH = 1280;
    private static final int DEFAULT_HEIGHT = 800;
    private static final int MINIMUM_WIDTH = 800;
    private static final int MINIMUM_HEIGHT = 700;
    private static final int FONT_SIZE = 14;

    /** Scene Logo path */
    public static final String LOGO = "images" + FileHelper.FILE_SEPARATOR + "darklogo.png";

    private static SceneManager _instance = new SceneManager();

    private Stage _rootStage;
    private Stack<Scenes> _history = new Stack<Scenes>();
    private Scenes _currentScene;
    private final Map<Scenes, Parent> _scenes = new HashMap<Scenes, Parent>();

    /** Enum for each scene with a filename and name */
    public enum Scenes {
        /** Splash Scene */
        SPLASH_SCREEN("SplashScreen.fxml"),
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
        /** Category chooser scene */
        CATEGORY_CHOOSER("CategoryChooser.fxml"),
        /** Help Screen scene */
        HELP_SCREEN("HelpScreen.fxml"),
        /** Edit Menu scene */
        EDIT_MENU("EditMenu.fxml"),
        /** Offline menu scene */
        OFFLINE("Offline.fxml"),
        /** Opening Menu scene */
        OPENING_MENU("OpeningMenu.fxml"),
        /** Customise scene */
        CUSTOMISE_MENU("CustomiseMenu.fxml"),
        /** Stats scene */
        STATS_SCREEN("Stats.fxml"),
        /** Online scene */
        ONLINE_MENU("OnlineMenu.fxml"),
        /** Create game scene */
        CREATE_GAME("CreateGame.fxml"),
        /** Lobby screen scene */
        LOBBY_SCREEN("LobbyScreen.fxml"),
        /** Countdown screen before question */
        COUNTDOWN("CountDown.fxml"),
        /** Eject screen after question */
        EJECT("EjectScreen.fxml"),
        /** Eject screen after question */
        REMAINING("RemainingScreen.fxml"),
        /** Online Question screen shows question */
        ONLINE_QUESTION("OnlineQuestion.fxml"),
        /** Game over for online */
        GAME_OVER("GameOver.fxml"),
        /** Info scene */
        INFO("Info.fxml"),
        /** Chat scene */
        CHAT_SCREEN("ChatScreen.fxml");

        private final String _filename;

        Scenes(final String filename) {
            _filename = filename;
        }

        @Override
        public String toString() {
            return _filename;
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
        Font.loadFont(getClass().getResource("/" + FOLDER + "/resources/joffrey.ttf").toExternalForm(), FONT_SIZE);
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
        // tilesfx need to be load using javafx thread
        if (scene == Scenes.STATS_SCREEN) {
            loadScene(scene);
            return;
        }
        new Thread(() -> {
            loadScene(scene);
        }).start();
    }

    private void loadScene(final Scenes scene) throws RuntimeException {
        Parent next = _scenes.computeIfAbsent(scene, k -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(getPath(k._filename)));
                Parent root = loader.load();
                return root;
            } catch (IOException ex) {
                Platform.runLater(() -> {
                    new ExceptionAlert(ex);
                });
                throw new RuntimeException(ex);
            }
        });
        _history.push(scene);
        _currentScene = scene;
        Platform.runLater(() -> {

            if (_rootStage.getScene() == null) {
                _rootStage.setScene(new Scene(next));
                return;
            }
            _rootStage.getScene().setRoot(next);
        });
    }

    /**
     * Used to clean switch current scene (reload)
     * 
     * @param scene Scenes enum
     */
    public void cleanSwitchScene(final Scenes scene) {
        unloadScene(scene);
        switchScene(scene);
    }

    /**
     * Used to switch back scene by popping current scene from the history
     */
    public void backScene() {
        _history.pop();
        switchScene(_history.pop());
    }

    /**
     * Used to switch back scene twice by popping current scene from the history
     */
    public void backSceneTwice() {
        _history.pop();
        if (_history.peek() == Scenes.SPLASH_SCREEN) {
            _history.pop();
        }
        switchScene(_history.pop());
    }

    /**
     * Used to unload scene
     */
    public void unloadScene() {
        _scenes.remove(_history.peek());
    }

    /**
     * Used to unload all scenes
     */
    public void unloadAllScenes() {
        for (Scenes scenes : _history) {
            _scenes.remove(scenes);
        }
    }

    /**
     * Used to unload a particular scene
     * 
     * @param scene
     */
    public void unloadScene(final Scenes scene) {
        _scenes.remove(scene);
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
     * Used to get current scene
     * 
     * @return current scene
     */
    public Scenes getCurrentScene() {
        return _currentScene;
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

    /**
     * Used to return the root stage
     * 
     * @return Stage
     */
    public Stage getRootStage() {
        return _rootStage;
    }
}
