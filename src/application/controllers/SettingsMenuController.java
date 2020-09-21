package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * This class is the SettingsMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SettingsMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Slider _musicSlider;

    @FXML
    private Label _musicValue;

    @FXML
    private Slider _speakSlider;

    @FXML
    private Label _speakValue;

    @FXML
    private Slider _speedSlider;

    @FXML
    private Label _speedValue;

    @FXML
    void handleBackButton(ActionEvent event) {
        _sceneManager.backScene();
    }

    @FXML
    void handleResetButton(ActionEvent event) {
        // TODO
    }

    @FXML
    void handleSaveButton(ActionEvent event) {
        // TODO
    }

}
