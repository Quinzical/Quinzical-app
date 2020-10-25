package quinzical.models.api;

/**
 * This class is used to store Login entry data retieved from API
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LoginEntry {
    private String _username;
    private String _userID;
    private String _jwtToken;

    /**
     * Construct Login Entry object to store api data
     * 
     * @param username
     * @param userID
     * @param jwtToken
     */
    public LoginEntry(final String username, final String userID, final String jwtToken) {
        _username = username;
        _userID = userID;
        _jwtToken = jwtToken;
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

    /**
     * @return String return the _jwtToken
     */
    public String getJwtToken() {
        return _jwtToken;
    }

    /**
     * @param jwtToken the _jwtToken to set
     */
    public void setJwtToken(final String jwtToken) {
        _jwtToken = jwtToken;
    }

}
