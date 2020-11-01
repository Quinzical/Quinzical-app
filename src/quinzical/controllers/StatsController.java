package quinzical.controllers;

import quinzical.util.SceneManager;
import quinzical.util.models.LoginModel;
import quinzical.util.sql.data.GameStatsData;
import quinzical.util.sql.data.UserStatsData;
import quinzical.util.sql.db.StatsDB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * This class is the Stats screen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class StatsController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    private static final double PERCENTAGE = 100.0;

    private final StatsDB _statsDB = new StatsDB();

    @FXML
    private AnchorPane _tilesPane;

    @FXML
    private Tile _lastGameAccuracy;

    @FXML
    private Tile _totalAccuracy;

    @FXML
    private Tile _scoreChart;

    @FXML
    private Tile _categoryChart;

    /**
     * initialize with Stats.fxml
     */
    public void initialize() {
        setScoreChart();
        setAccuracy();
        setCategoryChart();
    }

    private void setAccuracy() {
        try {
            UserStatsData stats = _statsDB.getUserStatsData(_login.getUserID());
            _lastGameAccuracy.setValue(_statsDB.getLastCorrectAttempts(_login.getUserID()) * PERCENTAGE
                    / _statsDB.getLastTotalAttempts(_login.getUserID()));
            _totalAccuracy.setValue(stats.getCorrectAttempts() * PERCENTAGE / stats.getAttempts());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCategoryChart() {
        try {
            List<BarChartItem> categories = _statsDB.getUserCategoryStats(_login.getUserID());
            if (categories.size() == 0) {
                return;
            }

            // CHECKSTYLE:OFF
            Tile categoryChart = TileBuilder.create().skinType(Tile.SkinType.BAR_CHART).title("Category Answered")
                    .animated(true).barChartItems(categories.toArray(new BarChartItem[0]))
                    .maxValue(categories.stream().mapToInt(v -> (int) v.getValue()).max().getAsInt()).build();
            _tilesPane.getChildren().add(categoryChart);
            categoryChart.setMinWidth(607.0);
            categoryChart.setLayoutX(14);
            categoryChart.setLayoutY(275);
            // CHECKSTYLE:ON

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setScoreChart() {
        try {
            List<GameStatsData> datas = _statsDB.getUserGameStats(_login.getUserID());
            List<ChartData> chartdatas = new ArrayList<ChartData>();
            int max = 0;
            if (datas.size() > 0) {
                datas.remove(datas.size() - 1);
            }

            for (GameStatsData data : datas) {
                if (data.getScore() > max) {
                    max = data.getScore();
                }
                chartdatas.add(new ChartData(data.getScore()));
            }
            // CHECKSTYLE:OFF
            Tile categoryChart = TileBuilder.create().skinType(Tile.SkinType.SMOOTH_AREA_CHART).title("Score")
                    .decimals(0).unit(" Points").animated(true).chartData(chartdatas.toArray(new ChartData[0])).build();
            _tilesPane.getChildren().add(categoryChart);
            categoryChart.setMinWidth(607.0);
            // categoryChart.setMinWidth(450.0);
            categoryChart.setLayoutX(14);
            categoryChart.setLayoutY(14);
            // CHECKSTYLE:ON
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
