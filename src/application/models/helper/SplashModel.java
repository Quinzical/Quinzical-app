package application.models.helper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.api.LeaderboardModel;
import application.models.game.international.InternationalModel;
import application.models.login.LoginModel;
import application.models.sql.SQLConnection;
import application.processes.APILoader;
import javafx.concurrent.Task;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the splash screen based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SplashModel {

    private final ExecutorService _team = Executors.newSingleThreadExecutor();
    private final SceneManager _sceneManager = SceneManager.getInstance();

    private static SplashModel _instance;

    private Scenes _nextScene = Scenes.LOGIN_SCREEN;
    private Pages _page = Pages.SQL;

    private SplashModel() {
    }

    /** Enum for each scene with a filename and name */
    public enum Pages {
        /** International */
        INTERNATIONAL(),
        /** Leaderboard */
        LEADERBOARD(),
        /** SQL */
        SQL();

        Pages() {
        }
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return SplashModel
     */
    public static SplashModel getInstance() {
        if (_instance == null) {
            _instance = new SplashModel();
        }
        return _instance;
    }

    /**
     * Used to set the next scene after the splash screen.
     * 
     * @param nextScene
     * @param page
     */
    public void setNextScene(final Scenes nextScene, final Pages page) {
        _nextScene = nextScene;
        _page = page;
    }

    /**
     * Used to set the time consuming method that needs to be carried out.
     */
    public void doMethod() {
        switch (_page) {
            case SQL:
                _team.submit(() -> SQLConnection.getInstance());
                _team.submit(() -> LoginModel.getInstance());
                _team.submit(new APILoader());
                break;
            case INTERNATIONAL:
                _team.submit(() -> InternationalModel.getInstance().retrieveQuestion());
                break;
            case LEADERBOARD:
                _team.submit(() -> LeaderboardModel.getInstance().loadLeaderboard());
                break;
            default:
                break;
        }
        _team.submit(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                return null;
            }

            @Override
            protected void succeeded() {
                _sceneManager.unloadScene(Scenes.SPLASH_SCREEN);
                _sceneManager.switchScene(_nextScene);
            }
        });
    }
}
