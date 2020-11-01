package quinzical.util.api;

import javafx.scene.image.ImageView;
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

    private ImageView _imageView;

    private ImageEntry _imageEntry;

    private Image _image = new Image();

    private Sheep _currentSheep;

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
     * Used to set sheep ImageView
     * 
     * @param sheep
     */
    public void setImageView(final ImageView sheep) {
        _imageView = sheep;
    }

    /**
     * Used to get sheep ImageView
     * 
     * @return sheep
     */
    public ImageView getImageView() {
        return _imageView;
    }

    /**
     * Used to return the users sheep after being loaded from the api.=
     * 
     * @return sheep
     */
    public Sheep getSheep() {
        if (_imageEntry == null) {
            return Sheep.WHITE;
        } else {
            return _imageEntry.getSheep();
        }
    }

    /**
     * Used to set the users new sheep
     * 
     * @param sheep
     */
    public void setSheep(final Sheep sheep) {
        _currentSheep = sheep;
    }

    /**
     * Used to post sheep image
     */
    public void postImage() {
        if (_currentSheep != null) {
            _image.postImage(_currentSheep.getImageName());
        }
    }

    /**
     * Used to get the users image from the api
     */
    public void getImage() {
        Image image = new Image();
        LoginModel login = LoginModel.getInstance();

        _imageEntry = image.getImage(login.getJwtToken());
    }
}
