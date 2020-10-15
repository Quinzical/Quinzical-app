package application.models.sql.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.controllers.helper.GameStateData;
import application.models.sql.SQLConnection;
import application.models.sql.data.GameSessionData;

public class GameSessionDB {
    /**
     * Used to insert a user to database
     * 
     * @param userID
     * @param categories list of 5 category id in string e.g 1,2,3,4,5
     *                   1000,2323,1231,2313,43523
     * @throws SQLException
     * @return id
     */
    public int insert(final int userID, final String categories) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO game_sessions(user_id, categories, questions, score) VALUES(?,?,?,0)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        pstmt.setString(2, categories);
        pstmt.setString(3, "0,0,0,0,0");
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        SQLConnection.closeConnection(conn);
        return id;
    }

    /**
     * Used to get a game session by gameSessionID
     * 
     * @param id
     * @return GameSesssion
     * @throws SQLException
     */
    public GameSessionData query(final int id) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, user_id, categories, questions, score FROM game_sessions WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        GameSessionData session = null;
        if (rs.next()) {
            session = new GameSessionData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("categories"),
                    rs.getString("questions"), rs.getInt("score"));
        }

        SQLConnection.closeConnection(conn);
        return session;
    }

    /**
     * Used to get all game sessions
     * 
     * @return List of GameSesssion
     */
    public List<GameSessionData> query() throws SQLException {
        List<GameSessionData> users = new ArrayList<GameSessionData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, user_id, categories, questions, score FROM game_sessions";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            users.add(new GameSessionData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("categories"),
                    rs.getString("questions"), rs.getInt("score")));
        }

        return users;
    }

    /**
     * Used to update a game session by gameSessionID
     * 
     * @param data GameSessionData
     * @throws SQLException
     */
    public void update(final GameSessionData data) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "UPDATE game_sessions SET user_id=?, categories=?, questions=?, score=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, data.getUserID());
        pstmt.setString(2, data.getCategoriesString());
        pstmt.setString(3, data.getQuestionsString());
        pstmt.setInt(4, data.getScore());
        pstmt.setInt(5, data.getID());

        pstmt.executeUpdate();
        SQLConnection.closeConnection(conn);
    }

    /**
     * Generates GameStateData from sql db which includes question attempts
     * 
     * @param categories
     * @param gameSessionID
     * @return GameStateData
     * @throws SQLException
     */
    public GameStateData getGameState(final int[] categories, final int gameSessionID) throws SQLException {
        int[][] state = new int[5][5];
        int[] count = new int[5];
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, game_session_id, category_id, question_id, score, correct FROM attempts WHERE game_session_id=? ORDER BY id";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, gameSessionID);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < categories.length; i++) {
                if (categories[i] == rs.getInt("category_id")) {
                    state[i][count[i]++] = rs.getInt("correct");
                    break;
                }
            }
        }
        return new GameStateData(state);
    }

    /**
     * Used to insertAttempts for GameSessions
     * 
     * @param gameSessionID
     * @param categoryID
     * @param questionID
     * @param score
     * @param correct
     * @return id of attempt
     * @throws SQLException
     */
    public int insertAttempt(final int gameSessionID, final int categoryID, final int questionID, final int score,
            final boolean correct) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO attempts(game_session_id, category_id, question_id, score, correct) VALUES(?,?,?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, gameSessionID);
        pstmt.setInt(2, categoryID);
        pstmt.setInt(3, questionID);
        pstmt.setInt(4, score);
        pstmt.setBoolean(5, correct);
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        GameSessionData gameSession = query(gameSessionID);
        if (correct) {
            gameSession.setScore(gameSession.getScore() + score);
        }
        gameSession.setCategoriesQuestionNumber(categoryID, correct);
        update(gameSession);

        SQLConnection.closeConnection(conn);
        return id;
    }

    /**
     * Used to get gameSessionID for the current user.
     * 
     * @param userID
     * @return int gameSessionID for a particular user
     */
    public int getGameSessionID(final int userID) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT * FROM game_sessions WHERE user_id=? ORDER BY id DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Integer.toString(userID));
        ResultSet rs = pstmt.executeQuery();

        GameSessionData session = null;
        if (rs.next()) {
            session = new GameSessionData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("categories"),
                    rs.getString("questions"), rs.getInt("score"));
        }

        pstmt.close();
        rs.close();
        SQLConnection.closeConnection(conn);

        return session.getID();
    }
}
