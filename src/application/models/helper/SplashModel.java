package application.models.helper;

import application.helper.SceneManager.Scenes;
import application.models.api.LeaderboardModel;
import application.models.game.international.InternationalModel;
import application.models.sql.SQLConnection;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the splash screen based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SplashModel {

    private static SplashModel _instance;

    private Scenes _nextScene = Scenes.LOGIN_SCREEN;
    private Pages _page = Pages.SQL;

    private SplashModel() {
    }

    /** Enum for each scene with a filename and name */
    public enum Pages {
        /** International */
        INTERNATIONAL(),
        /** SQL */
        SQL(),
        /** Leaderboard */
        LEADERBOARD();

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
     * Used to get the next scene after the splash screen.
     * 
     * @return Scenes
     */
    public Scenes getNextScene() {
        return _nextScene;
    }

    /**
     * Used to set the time consuming method that needs to be carried out.
     */
    public void doMethod() {
        switch (_page) {
            case SQL:
                SQLConnection.getInstance();
                break;
            case INTERNATIONAL:
                InternationalModel.getInstance().retrieveQuestion();
                break;
            case LEADERBOARD:
                LeaderboardModel.getInstance().loadLeaderboard();
                break;
            default:
                break;
        }
    }
}
