package quinzical.models.api;

/**
 * This class is used to store Leaderboard entry data retieved from API
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LeaderboardEntry {
    private String _username;
    private String _categories;
    private int _score;

    /**
     * Construct Leaderboard Entry object to store api data
     * 
     * @param username
     * @param categories
     * @param score
     */
    public LeaderboardEntry(final String username, final String categories, final int score) {
        _username = username;
        _categories = categories;
        _score = score;
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
