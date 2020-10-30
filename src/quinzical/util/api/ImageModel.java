package quinzical.util.api;

import quinzical.controllers.util.Sheep;
import quinzical.util.models.LoginModel;

/**
 * Leaderboard used to call REST API for Global Leaderboard Information
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class ImageModel {

    private static ImageModel _instance;

    private Image _image = new Image();

    private ImageModel() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return ImageModel
     */
    public static ImageModel getInstance() {
        if (_instance == null) {
            _instance = new ImageModel();
        }
        return _instance;
    }

    /**
     * Used to post sheep image
     * 
     * @param sheep
     */
    public void postImage(final Sheep sheep) {
        _image.postImage(sheep.getImageName());
    }

    /**
     * Used to get the users image from the api
     * 
     * @return Sheep
     */
    public Sheep getImage() {
        Image image = new Image();
        LoginModel login = LoginModel.getInstance();

        ImageEntry imageEntry = image.getImage(login.getJwtToken());

        if (imageEntry == null) {
            return Sheep.WHITE;
        } else {
            return imageEntry.getSheep();
        }
    }
}
