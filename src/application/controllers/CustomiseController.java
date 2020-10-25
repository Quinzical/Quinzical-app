package application.controllers;

import application.controllers.helper.Sheep;
import application.controllers.helper.StarBackground;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
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

    private static final int WHITE_POS = 6;

    private Sheep[] _images = {Sheep.BLUE, Sheep.CYAN, Sheep.GREEN, Sheep.ORANGE, Sheep.PINK, Sheep.RED, Sheep.WHITE, Sheep.YELLOW};

    private static int _index = WHITE_POS;

    @FXML
    private Label _userScore;

    @FXML
    private ImageView _sheepImage;

    @FXML
    private Button _menuButton;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * Used to initialize CustomiseController
     */
    public void initialize() {
        _sheepImage.setImage(new Image(_images[_index].getFilename()));
        StarBackground.animate(_background1, _background2, _background3);
    }

    @FXML
    private void handleBackButton(final ActionEvent event) {
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
