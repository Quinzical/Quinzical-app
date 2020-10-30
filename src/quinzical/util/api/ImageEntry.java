package quinzical.util.api;

import quinzical.controllers.util.Sheep;

/**
 * This class is used to store image entry data retieved from API
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ImageEntry {

    private String _imageUri;
    private String _userID;
    private String _jwtToken;

    /**
     * Construct Login Entry object to store api data
     * 
     * @param imageUri
     * @param userID
     * @param jwtToken
     */
    public ImageEntry(final String imageUri, final String userID, final String jwtToken) {
        _imageUri = imageUri;
        _userID = userID;
        _jwtToken = jwtToken;
    }

    /**
     * @return Sheep return the users chosen sheep
     */
    public Sheep getSheep() {
        return Sheep.fromString(_imageUri);
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
