package quinzical.models.sql.data;

/**
 * UserStatsData used to store User Stats data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class UserStatsData {
    private int _id;
    private int _games;
    private int _attempts;
    private int _totalScore;
    private int _correctAttempts;
    private int _correctTotalScore;


    /**
     * Calculated user stats
     * @param id
     * @param games
     * @param attempts
     * @param totalScore
     * @param correctAttempts
     * @param correctTotalScore
     */
    public UserStatsData(final int id, final int games, final int attempts, final int totalScore, final int correctAttempts, final int correctTotalScore) {
        _id = id;
        _games = games;
        _attempts = attempts;
        _totalScore = totalScore;
        _correctAttempts = correctAttempts;
        _correctTotalScore = correctTotalScore;
    }

    /**
     * @return int return the id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return int return the _games
     */
    public int getGames() {
        return _games;
    }

    /**
     * @return int return the _attempts
     */
    public int getAttempts() {
        return _attempts;
    }

    /**
     * @return int return the _totalScore
     */
    public int getTotalScore() {
        return _totalScore;
    }

    /**
     * @return int return the _correctAttempts
     */
    public int getCorrectAttempts() {
        return _correctAttempts;
    }

    /**
     * @return int return the _correctTotalScore
     */
    public int getCorrectTotalScore() {
        return _correctTotalScore;
    }
}
