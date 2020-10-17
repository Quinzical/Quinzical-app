package application.models.api;

/**
 * This class is used to store Login entry data retieved from API
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LoginEntry {
    private String _username;
    private String _userID;

    /**
     * Construct Login Entry object to store api data
     * 
     * @param username
     * @param userID
     */
    public LoginEntry(final String username, final String userID) {
        _username = username;
        _userID = userID;
    }

    /**
     * @return String return the username
     */
    public String getUsername() {
        return _username;
    }

    /**
     * @return String return the userID
     */
    public String getUserID() {
        return _userID;
    }
}
