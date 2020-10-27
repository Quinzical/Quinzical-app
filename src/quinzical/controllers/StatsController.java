package quinzical.controllers;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.models.LoginModel;
import quinzical.util.sql.data.GameStatsData;
import quinzical.util.sql.data.UserStatsData;
import quinzical.util.sql.db.StatsDB;

import java.sql.SQLException;
import java.util.List;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
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
    private final LoginModel _login = LoginModel.getInstance();

    private static final double PERCENTAGE = 100.0;

    private final StatsDB _statsDB = new StatsDB();

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

            for (BarChartItem category : categories) {
                _categoryChart.addBarChartItem(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setScoreChart() {
        try {
            List<GameStatsData> datas = _statsDB.getUserGameStats(_login.getUserID());
            datas.remove(datas.size() - 1);

            for (GameStatsData data : datas) {
                _scoreChart.addChartData(new ChartData(data.getScore()));
            }
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
