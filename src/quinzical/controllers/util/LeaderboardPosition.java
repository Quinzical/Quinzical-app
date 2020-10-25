package quinzical.controllers.helper;

import quinzical.models.api.LeaderboardEntry;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * This class is for LeaderboardPosition for choosing which leaderboard to view
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LeaderboardPosition {

    private LeaderboardEntry _entry;
    private int _position;
    private static final int DEFAULT_WIDTH = 145;
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_COLUMN_200 = 200;
    private static final int DEFAULT_COLUMN_75 = 75;
    private static final String DEFAULT_FONT_SIZE = "-fx-font-size:25";

    /**
     * Create Leaderboard with Positions
     * 
     * @param entry
     * @param position
     */
    public LeaderboardPosition(final LeaderboardEntry entry, final int position) {
        _entry = entry;
        _position = position;
    }

    /**
     * GridPane for leaderboard
     * 
     * @return gridpane
     */
    public GridPane getComponent() {
        GridPane gridpane = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints(DEFAULT_COLUMN_200);
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints(DEFAULT_COLUMN_200);
        ColumnConstraints column3 = new ColumnConstraints(DEFAULT_COLUMN_75);
        gridpane.getColumnConstraints().addAll(column1, column2, column3);

        Button placing = new Button();
        placing.setPrefWidth(DEFAULT_WIDTH);
        placing.setPrefHeight(DEFAULT_HEIGHT);
        placing.setStyle(DEFAULT_FONT_SIZE);
        placing.setText(Integer.toString(_position));

        Label username = new Label(_entry.getUsername());
        username.setStyle(DEFAULT_FONT_SIZE);
        Label score = new Label(Integer.toString(_entry.getScore()));
        score.setStyle(DEFAULT_FONT_SIZE);

        int row = _position - 1;
        int col = 0;
        gridpane.add(placing, col, row);
        col++;
        gridpane.add(username, col, row);
        col++;
        gridpane.add(score, col, row);
        return gridpane;
    }

}
