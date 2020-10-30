package quinzical.controllers;

import quinzical.controllers.util.Sheep;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.api.ImageModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is the Customise Screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class CustomiseController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private final ImageModel _imageModel = ImageModel.getInstance();

    private static final int WHITE_POS = 6;

    private Sheep[] _images = { Sheep.BLUE, Sheep.CYAN, Sheep.GREEN, Sheep.ORANGE, Sheep.PINK, Sheep.RED, Sheep.WHITE,
            Sheep.YELLOW };

    private Sheep _currentSheep;

    private static int _index = WHITE_POS;

    @FXML
    private Label _userScore;

    @FXML
    private ImageView _sheepImage;

    @FXML
    private Button _menuButton;

    /**
     * Used to initialize CustomiseController
     */
    public void initialize() {
        _currentSheep = _imageModel.getImage();

        if (_currentSheep == null) {
            _sheepImage.setImage(new Image(_images[WHITE_POS].getFilename()));
        } else {
            _sheepImage.setImage(new Image(_currentSheep.getFilename()));
        }

        int count = 0;
        for (Sheep sheep : _images) {
            if (sheep == _currentSheep) {
                _index = count;
            }
            count++;
        }
    }

    @FXML
    private void handleBackButton(final ActionEvent event) {
        _imageModel.postImage(_images[_index]);
        _sceneManager.unloadScene();
        _sceneManager.backScene();
    }

    @FXML
    private void handleLeftArrow(final ActionEvent event) {
        if (_index == 0) {
            _index = _images.length - 1;
        } else {
            _index--;
        }
        _sheepImage.setImage(new Image(_images[_index].getFilename()));
    }

    @FXML
    private void handleRightArrow(final ActionEvent event) {
        if (_index == _images.length - 1) {
            _index = 0;
        } else {
            _index++;
        }
        _sheepImage.setImage(new Image(_images[_index].getFilename()));
    }

    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }
}
