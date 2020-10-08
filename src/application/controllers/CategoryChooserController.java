package application.controllers;

import application.helper.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public class CategoryChooserController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private TilePane _possibleCategoriesPane;

    @FXML
    private TilePane _chosenCategoriesPane;


    /**
     * initialize with CategoryChooser.fxml
     */
    public void initialize() {
        // TODO
    }

    /**
     * Used to handle back button
     *
     * @param event
     */
    @FXML
    private void handleBackButton(ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HOME_MENU);
    }

    /**
     * Used to handle Leaderboard button
     *
     * @param event
     */
    @FXML
    private void handleLeaderboardButton(ActionEvent event) {
    	_sceneManager.switchScene(SceneManager.Scenes.LEADERBOARD);
    }

    /**
     * Used to handle setting button
     *
     * @param event
     */
    @FXML
    private void handleSettingsButton(ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.SETTINGS_MENU);
    }
}
