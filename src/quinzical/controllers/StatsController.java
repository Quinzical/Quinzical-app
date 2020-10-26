package quinzical.controllers;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import javafx.application.Platform;
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
    private Tile _lastGameAccuracy;

    @FXML
    private Tile _totalAccuracy;

    @FXML
    private Tile _scoreChart;

    @FXML
    private Tile _categoryChart;

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

        _lastGameAccuracy.setValue(10);

        BarChartItem barChartItem1 = new BarChartItem("Places", 100);
        BarChartItem barChartItem2  = new BarChartItem("People", 65);
        _categoryChart.setText("test");

        _categoryChart.addBarChartItem(barChartItem1);
        _categoryChart.addBarChartItem(barChartItem2);
        System.out.println();


        _scoreChart.addChartData(new ChartData(100));
        _scoreChart.addChartData(new ChartData(23));

        _scoreChart.addChartData(new ChartData(23));
        _scoreChart.addChartData(new ChartData(23));

        System.out.println("test");

    }

    /**
     * Used to handle back to original scene before settings
     * 
     * @param event
     */
    @FXML
    void handleBackButton(final ActionEvent event) {
        _sceneManager.unloadScene();
        _sceneManager.backScene();
    }
}
