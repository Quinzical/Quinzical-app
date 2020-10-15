package application.models.sql.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.models.sql.SQLConnection;
import application.models.sql.data.GameStatsData;
import application.models.sql.data.UserStatsData;

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
        String sql = "SELECT g.id, g.user_id, u.name, g.categories, g.score FROM game_sessions AS g, users AS u WHERE questions='5,5,5,5,5' AND g.user_id=u.id";

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
     * Get all local user stats
     *
     * @return List of userstats
     * @throws SQLException
     */
    public List<UserStatsData> getAllUserStats() throws SQLException {
        List<UserStatsData> stats = new ArrayList<UserStatsData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT u.id, ( SELECT COUNT(*) FROM game_sessions WHERE user_id = u.id ) as games, "
                + "( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id) as attempts, "
                + "( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id) as total_score, "
                + "( SELECT COUNT(a.id) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1) as correct_attempts, "
                + "( SELECT SUM(a.score) FROM game_sessions as g, attempts as a WHERE user_id = u.id AND a.game_session_id=g.id AND a.correct=1) as correct_total_score "
                + "FROM users as u";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stats.add(new UserStatsData(rs.getInt("id"), rs.getInt("games"), rs.getInt("attempts"),
                    rs.getInt("total_score"), rs.getInt("correct_attempts"), rs.getInt("correct_total_score")));
        }

        SQLConnection.closeConnection(conn);
        return stats;
    }
}
