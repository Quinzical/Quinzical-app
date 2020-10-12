package application.models.login;

/**
 * This class is used to manage and store login data, this class also prepares
 * game sessions when load
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class LoginModel {

    private static LoginModel _instance;

    private String _username;
    private int _userID;
    private int _gameSessionID;

    private LoginModel() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return QuestionModel
     */
    public static LoginModel getInstance() {
        if (_instance == null) {
            _instance = new LoginModel();
        }
        return _instance;
    }

    /**
     * Used to set or change user in the singleton
     * 
     * @param username
     * @param userID
     * @param gameSessionID
     */
    public void setUser(final String username, final int userID, final int gameSessionID) {
        _username = username;
        _userID = userID;
        _gameSessionID = gameSessionID;
    }

    /**
     * @return String return the _username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return int return the _userID
     */
    public int getUserID() {
        return _userID;
    }

    /**
     * @return int return the _gameSessionID
     */
    public int getGameSessionID() {
        return _gameSessionID;
    }

    /**
     * Used to set gameSessionID when reseting
     * @param gameSessionID
     */
    public void setGameSessionID(final int gameSessionID) {
        _gameSessionID = gameSessionID;
    }

}
