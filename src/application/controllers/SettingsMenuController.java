package application.controllers;

import application.helper.SceneManager;
import application.models.SettingsModel;
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
    
    private final static int DEFAULT_VOLUME = 50;
    
    // Equates to espeak speed of 175 wpm, the default setting
    private final static int DEFAULT_SPEED = 26;

    @FXML
    private Slider _speakSlider;

    @FXML
    private Label _speakValue;

    @FXML
    private Slider _speedSlider;

    @FXML
    private Label _speedValue;

    /**
     * Initialize with SettingsMenu.fxml
     */
    public void initialize() {
        _speakSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _speakValue.setText(String.valueOf(newValue.intValue()));
                SettingsModel.setEspeakVolume(newValue.intValue());
            }
        });

        _speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                _speedValue.setText(String.valueOf(newValue.intValue()));
                SettingsModel.setEspeakSpeed(newValue.intValue());
            }
        });
    }

    @FXML
    void handleBackButton(ActionEvent event) {
        _sceneManager.backScene();
    }

    @FXML
    void handleResetButton(ActionEvent event) {
    	_speakValue.setText(String.valueOf(DEFAULT_VOLUME));
    	_speedValue.setText(String.valueOf(DEFAULT_SPEED));
    	
    	_speakSlider.setValue(DEFAULT_VOLUME);
    	_speedSlider.setValue(DEFAULT_SPEED);
    	
    	SettingsModel.setEspeakVolume(DEFAULT_VOLUME);
    	SettingsModel.setEspeakSpeed(DEFAULT_SPEED);
    }
}
