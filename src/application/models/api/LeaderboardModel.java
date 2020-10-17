package application.models.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.controllers.helper.ExceptionAlert;
import application.models.sql.data.GameSessionData;
import application.models.sql.data.UserData;
import application.models.sql.db.GameSessionDB;
import application.models.sql.db.UserDB;

/**
 * This class is used to store and get Leaderboard entry datafrom API
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class LeaderboardModel {

    private static LeaderboardModel _instance;

    private Leaderboard _leaderboard = new Leaderboard();

    private boolean _global = false;

    private LeaderboardModel() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return LeaderboardModel
     */
    public static LeaderboardModel getInstance() {
        if (_instance == null) {
            _instance = new LeaderboardModel();
        }
        return _instance;
    }

    /**
     * Used to set whether the leaderboard being viewed is the global or local one.
     * Set to true if global, false if local.
     * 
     * @param globalOrNot
     */
    public void setGlobal(final boolean globalOrNot) {
        _global = globalOrNot;
    }

    /**
     * Used to get the header text for the leaderboard based on the users choice.
     * 
     * @return String
     */
    public String getHeader() {
        if (_global) {
            return "Global Leaderboard";
        } else {
            return "Local Leaderboard";
        }
    }

    /**
     * Returns the current leaderboard.
     * 
     * @return List<LeaderboardEntry>
     */
    public List<LeaderboardEntry> getLeaderboard() {
        List<LeaderboardEntry> entries = new ArrayList<LeaderboardEntry>();
        if (_global) {
            entries = _leaderboard.getLeaderboard();
        } else {
            GameSessionDB gameSessionDB = new GameSessionDB();
            List<GameSessionData> sessions = null;
            try {
                sessions = gameSessionDB.getLeaderboardList();
            } catch (SQLException e) {
                new ExceptionAlert(e);
                e.printStackTrace();
            }

            UserDB userDB = new UserDB();
            for (GameSessionData gameData : sessions) {
                UserData user = null;
                try {
                    user = userDB.query(gameData.getUserID());
                } catch (SQLException e) {
                    new ExceptionAlert(e);
                    e.printStackTrace();
                }

                if (user != null) {
                    entries.add(new LeaderboardEntry(user.getName(), gameData.getCategoriesString(), gameData.getScore()));
                }
            }
        }
        return entries;
    }
}
