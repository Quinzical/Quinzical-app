package application.models.sql.data;

/**
 * GameStatsData used to store Game Stats data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameStatsData {
    private int _id;
    private int _userID;
    private String _username;
    private String _categories;
    private int _score;

    /**
     * Create score object to hold for leaderboard
     * 
     * @param id
     * @param userID
     * @param username
     * @param categories
     * @param score
     */
    public GameStatsData(final int id, final int userID, final String username, final String categories, final int score) {
        _id = id;
        _userID = userID;
        _username = username;
        _categories = categories;
        _score = score;
    }

    /**
     * @return int return the id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return int return the userID
     */
    public int getUserID() {
        return _userID;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return String return the categories
     */
    public String getCategories() {
        return _categories;
    }

    /**
     * @return int return the score
     */
    public int getScore() {
        return _score;
    }
}
