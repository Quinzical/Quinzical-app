package application.controllers;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * This class is the OpeningMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OpeningMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Label _usernameLabel;

    /**
     * Used to handle customise button
     * 
     * @param event
     */
    @FXML
    void handleCustomiseButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CUSTOMISE_MENU);
    }

    /**
     * Used to handle help button
     * 
     * @param event
     */
    @FXML
    void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }

    @FXML
    void handleInfoButton(final ActionEvent event) {

    }

    @FXML
    void handleLocalButton(final ActionEvent event) {

    }

    @FXML
    void handleMessageButton(final ActionEvent event) {

    }

    @FXML
    void handleOnlineButton(final ActionEvent event) {

    }

    @FXML
    void handleSettingsButton(final ActionEvent event) {

    }

    @FXML
    void handleStatsButton(final ActionEvent event) {

    }

}
