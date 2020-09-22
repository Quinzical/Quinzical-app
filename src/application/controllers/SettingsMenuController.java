package application.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    /**
     * initialize with SettingsMenu.fxml
     */
    public void initialize() {
        _musicSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _musicValue.setText(String.valueOf(newValue.intValue()));
            }
        });

        _speakSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _speakValue.setText(String.valueOf(newValue.intValue()));
            }
        });

        _speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _speedValue.setText(String.valueOf(newValue.intValue()));
            }
        });
    }

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
