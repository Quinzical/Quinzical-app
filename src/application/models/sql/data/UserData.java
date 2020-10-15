package application.models.sql.data;

public class UserData {
    private int _id;
    private String _name;
    private boolean _international;
    private int _gameSessionID;

    /**
     * Create User DB Object
     * 
     * @param id
     * @param name
     * @param international
     * @param gameSessionID
     */
    public UserData(final int id, final String name, final boolean international, final int gameSessionID) {
        _id = id;
        _name = name;
        _international = international;
        _gameSessionID = gameSessionID;
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
}
