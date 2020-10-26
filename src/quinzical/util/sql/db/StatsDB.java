package quinzical.util.sql.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eu.hansolo.tilesfx.skins.BarChartItem;
import quinzical.util.sql.SQLConnection;
import quinzical.util.sql.data.GameStatsData;
import quinzical.util.sql.data.UserStatsData;

/**
 * StatsDB used to handle Stats SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class StatsDB {
    /**
     * Get all local game stats
     * 
     * @return all stats
     * @throws SQLException
     */
    public List<GameStatsData> getAllStats() throws SQLException {
        List<GameStatsData> stats = new ArrayList<GameStatsData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT g.id, g.user_id, u.name, g.categories, g.score FROM game_sessions AS g, users AS u WHERE questions='5,5,5,5,5' AND g.user_id=u.id ORDER BY score DESC";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stats.add(new GameStatsData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("categories"), rs.getInt("score")));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }

    /**
     * Get all local game stats for graph in order
     * 
     * @param userID
     * @return all stats
     * @throws SQLException
     */
    public List<GameStatsData> getUserGameStats(final int userID) throws SQLException {
        List<GameStatsData> stats = new ArrayList<GameStatsData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT g.id, g.user_id, u.name, g.categories, g.score FROM game_sessions AS g, users AS u WHERE g.user_id=u.id AND u.id=? ORDER BY g.id ASC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            stats.add(new GameStatsData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("categories"), rs.getInt("score")));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }

    /**
     * Get all local user stats
     *
     * @return List of userstats
     * @throws SQLException
     */
    public List<UserStatsData> getAllUserStats() throws SQLException {
        List<UserStatsData> stats = new ArrayList<UserStatsData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT u.id, ( SELECT COUNT(*) FROM game_sessions WHERE user_id = u.id ) as games, "
                + "IFNULL(( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id), 0) as attempts, "
                + "IFNULL(( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id), 0) as total_score, "
                + "IFNULL(( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1), 0) as correct_attempts, "
                + "IFNULL(( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1), 0) as correct_total_score "
                + "FROM users as u ORDER BY ((total_score - correct_total_score)/total_score) DESC";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stats.add(new UserStatsData(rs.getInt("id"), rs.getInt("games"), rs.getInt("attempts"),
                    rs.getInt("total_score"), rs.getInt("correct_attempts"), rs.getInt("correct_total_score")));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }

    /**
     * Get all local user stats
     *
     * @param userID
     * @return List of userstats
     * @throws SQLException
     */
    public UserStatsData getUserStatsData(final int userID) throws SQLException {
        UserStatsData stats = null;

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT u.id, ( SELECT COUNT(*) FROM game_sessions WHERE user_id = u.id ) as games, "
                + "IFNULL(( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id), 0) as attempts, "
                + "IFNULL(( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id), 0) as total_score, "
                + "IFNULL(( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1), 0) as correct_attempts, "
                + "IFNULL(( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1), 0) as correct_total_score "
                + "FROM users as u WHERE u.id=? ORDER BY ((total_score - correct_total_score)/total_score) DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            stats = new UserStatsData(rs.getInt("id"), rs.getInt("games"), rs.getInt("attempts"),
                    rs.getInt("total_score"), rs.getInt("correct_attempts"), rs.getInt("correct_total_score"));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }

    /**
     * Get last game stats
     * 
     * @param userID
     * @return int
     * @throws SQLException
     */
    public int getLastCorrectAttempts(final int userID) throws SQLException {
        int attempts = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT COUNT(a.id) as count FROM game_sessions as g, attempts as a WHERE a.game_session_id=g.id AND a.correct=1 AND g.user_id=? AND a.game_session_id=(SELECT id FROM game_sessions ORDER BY id DESC LIMIT 1 )";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            attempts = rs.getInt("count");
        }

        SQLConnection.closeConnection(conn);
        return attempts;
    }

    /**
     * Get category and cound by order
     * 
     * @param userID
     * @return category
     * @throws SQLException
     */
    public List<BarChartItem> getUserCategoryStats(final int userID) throws SQLException {
        List<BarChartItem> items = new ArrayList<BarChartItem>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT c.name as name, COUNT(c.name) as count FROM categories as c, attempts as a, game_sessions as g WHERE g.user_id=? AND c.id=a.category_id AND g.id=a.game_session_id GROUP BY c.name ORDER BY count DESC";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            items.add(new BarChartItem(rs.getString("name"), rs.getInt("count")));
        }

        SQLConnection.closeConnection(conn);
        return items;
    }

    /**
     * Get last game stats
     * 
     * @param userID
     * @return int
     * @throws SQLException
     */
    public int getLastTotalAttempts(final int userID) throws SQLException {
        int attempts = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT COUNT(a.id) as count FROM game_sessions as g, attempts as a WHERE a.game_session_id=g.id AND g.user_id=? AND a.game_session_id=(SELECT id FROM game_sessions ORDER BY id DESC LIMIT 1 );";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            attempts = rs.getInt("count");
        }

        SQLConnection.closeConnection(conn);
        return attempts;
    }

    /**
     * Get all local game stats
     * 
     * @return all stats
     * @throws SQLException
     */
    public List<GameStatsData> getAllInternationalStats() throws SQLException {
        List<GameStatsData> stats = new ArrayList<GameStatsData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT g.id, g.user_id, u.name, g.categories, g.score FROM game_sessions AS g, users AS u WHERE questions='5,5,5,5,5' AND g.user_id=u.id ORDER BY score DESC";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stats.add(new GameStatsData(rs.getInt("id"), rs.getInt("user_id"), rs.getString("name"),
                    rs.getString("categories"), rs.getInt("score")));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }
}
