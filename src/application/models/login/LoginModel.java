package application.models.login;

import java.sql.SQLException;

import application.models.sql.data.UserData;
import application.models.sql.db.GameSessionDB;
import application.models.sql.db.UserDB;

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
    private int _gameSessionID = 0;
    private boolean _unlock;

    private UserDB _userDB = new UserDB();
    private GameSessionDB _gameSessionDB = new GameSessionDB();

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
     * @param unlock for international
     */
    public void setUser(final String username, final int userID, final int gameSessionID, final boolean unlock) {
        _username = username;
        _userID = userID;
        _gameSessionID = gameSessionID;
        _unlock = unlock;
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
     * 
     * @param gameSessionID
     */
    public void setGameSessionID(final int gameSessionID) {
        try {
            _userDB.setGameSessionID(_userID, gameSessionID);
            _gameSessionID = gameSessionID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to login user if they exist
     * 
     * @param username the username of the person trying to login
     * @return boolean
     * @throws SQLException
     */
    public boolean loginUser(final String username) throws SQLException {
        UserData user = _userDB.checkUser(username);

        if (user != null) {
            setUser(user.getName(), user.getID(), user.getGameSessionID(), user.getUnlock());
            _gameSessionID = user.getGameSessionID();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Used to login user if they exist
     * 
     * @param username the username of the person trying to login
     * @return boolean
     * @throws SQLException
     */
    public boolean checkUserExists(final String username) throws SQLException {
        UserData user = _userDB.checkUser(username);
        return user != null;
    }

    /**
     * Used to add a new user to the database.
     * 
     * @param username
     * @throws SQLException
     */
    public void registerUser(final String username) throws SQLException {
        int id = _userDB.insert(username);
        // Insert "0,0,0,0,0" for categories as the user has just been registered.
        _gameSessionDB.insert(id, "0,0,0,0,0");
    }

    /**
     * Used to check for international sections@
     * @return check if International is enabled
     */
    public boolean checkInternational() {
        return _unlock;
    }
}
