package quinzical.util.models;

import java.sql.SQLException;

import quinzical.util.sql.data.UserData;
import quinzical.util.sql.db.UserDB;

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
    private String _mongoID = "";
    private String _jwtToken;

    private UserDB _userDB = new UserDB();

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
     * @param unlock        for international
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
        _userDB.insert(username);
    }

    /**
     * Used to check for international sections
     * 
     * @return check if International is enabled
     */
    public boolean checkInternational() {
        return _unlock;
    }

    /**
     * Used to enable for international sections
     */
    public void enableInternational() {
        _unlock = true;
        try {
            _userDB.setUnlock(_userID, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to disable for international sections
     */
    public void disableInternational() {
        _unlock = false;
        try {
            _userDB.setUnlock(_userID, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to return the mongo id for the current user
     * 
     * @return String
     */
    public String getMongoID() {
        return _mongoID;
    }

    /**
     * Used to set the mongo id for the current user
     * 
     * @param mongoID
     */
    public void setMongoID(final String mongoID) {
        _mongoID = mongoID;
    }

    /**
     * Used to return the jwt token for the current session
     * 
     * @return String
     */
    public String getJwtToken() {
        return _jwtToken;
    }

    /**
     * Used to set the jwt token for the current session
     * 
     * @param jwtToken
     */
    public void setJwtToken(final String jwtToken) {
        _jwtToken = jwtToken;
    }
}
