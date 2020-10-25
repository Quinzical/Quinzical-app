package quinzical.controllers;

import quinzical.controllers.helper.StarBackground;
import quinzical.helper.SceneManager;
import eu.hansolo.tilesfx.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
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

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with Stats.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
    }

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