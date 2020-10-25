package quinzical.models.sql.data;

/**
 * UserData used to store User data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class UserData {
    private int _id;
    private String _name;
    private boolean _unlock;
    private int _gameSessionID;
    private int _internationalScore;

    /**
     * Create User DB Object
     * 
     * @param id
     * @param name
     * @param unlock
     * @param gameSessionID
     * @param internationalScore
     */
    public UserData(final int id, final String name, final boolean unlock, final int gameSessionID,
            final int internationalScore) {
        _id = id;
        _name = name;
        _unlock = unlock;
        _gameSessionID = gameSessionID;
        _internationalScore = internationalScore;
        _unlock = unlock;
    }

    /**
     * @return int return the _id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return String return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @return boolean return the _international
     */
    public Boolean getUnlock() {
        return _unlock;
    }

    /**
     * @return boolean return the _gameSessionID
     */
    public int getGameSessionID() {
        return _gameSessionID;
    }

    /**
     * @return int return the _internationScore
     */
    public int getInternationalScore() {
        return _internationalScore;
    }
}
