package application.controllers;

import application.helper.SceneManager;
import eu.hansolo.tilesfx.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

/**
 * This class is the Stats screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class StatsController {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    @FXML
    private Pane _tilesPane;

    @FXML
    private Tile _answerTime;

    @FXML
    private Tile _lastroundAccuracy;

    @FXML
    private Tile _questionAccuracy;

    @FXML
    private Tile _roundLength;

    @FXML
    private Tile _triesRadial;

    @FXML
    private Tile _totalTimePlayed;

    /**
     * Used to handle back to original scene before settings
     * 
     * @param event
     */
    @FXML
    void handleBackButton(final ActionEvent event) {
        _sceneManager.backScene();
    }
}
