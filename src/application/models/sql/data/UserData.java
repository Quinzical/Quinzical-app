package application.models.sql.data;

/**
 * UserData used to store User data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class UserData {
    private int _id;
    private String _name;
    private boolean _international;
    private int _gameSessionID;
    private int _internationalScore;

    /**
     * Create User DB Object
     * 
     * @param id
     * @param name
     * @param international
     * @param gameSessionID
     * @param internationalScore
     */
    public UserData(final int id, final String name, final boolean international, final int gameSessionID,
            final int internationalScore) {
        _id = id;
        _name = name;
        _international = international;
        _gameSessionID = gameSessionID;
        _internationalScore = internationalScore;
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
    public Boolean getInternational() {
        return _international;
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
