package quinzical.controllers;

import quinzical.controllers.util.alerts.ConfirmAlert;
import quinzical.controllers.util.Sheep;
import quinzical.controllers.util.SheepBackground;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.api.ImageModel;
import quinzical.util.JWTStore;
import quinzical.util.models.LoginModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * This class is the OpeningMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OpeningMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    @FXML
    private AnchorPane _pane;

    @FXML
    private AnchorPane _sheepPane;

    @FXML
    private Label _usernameLabel;

    @FXML
    private ImageView _sheep;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with OpeningMenu.fxml
     */
    public void initialize() {
        _usernameLabel.setText(_login.getUsername());
        _usernameLabel.getStyleClass().add("logingreen");
        StarBackground.animate(_background1, _background2, _background3);
        new SheepBackground(_sheepPane);

        Sheep currentSheep = ImageModel.getInstance().getImage();

        if (currentSheep == null) {
            _sheep.setImage(new Image(Sheep.WHITE.getFilename()));
        } else {
            _sheep.setImage(new Image(currentSheep.getFilename()));
        }
        ImageModel.getInstance().setImageView(_sheep);
    }

    /**
     * Used to handle customise button
     * 
     * @param event
     */
    @FXML
    private void handleCustomiseButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.CUSTOMISE_MENU);
    }

    /**
     * Used to handle help button
     * 
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }

    /**
     * Used to handle info button
     * 
     * @param event
     */
    @FXML
    private void handleInfoButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.INFO);
    }

    /**
     * Used to handle local play button
     * 
     * @param event
     */
    @FXML
    private void handleLocalButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    /**
     * Used to handle chat button
     * 
     * @param event
     */
    @FXML
    private void handleMessageButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CHAT_SCREEN);
    }

    /**
     * Used to handle online play button
     * 
     * @param event
     */
    @FXML
    private void handleOnlineButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    /**
     * Used to handle settings button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle stats button
     * 
     * @param event
     */
    @FXML
    private void handleStatsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.STATS_SCREEN);
    }

    /**
     * Used to handle exit button
     * 
     * @param event
     */
    @FXML
    private void handleExit(final ActionEvent event) {
        new ConfirmAlert("Quit the Game") {
            @Override
            protected void handleConfirm() {
                _sceneManager.close();
            }
        };
    }

    /**
     * Used to handle logout button
     * 
     * @param event
     */
    @FXML
    private void handleLogout(final ActionEvent event) {
        _sceneManager.unloadAllScenes();
        _sceneManager.switchScene(Scenes.LOGIN_SCREEN);
        JWTStore jwtStore = new JWTStore();
        jwtStore.setJWT("");
    }
}
