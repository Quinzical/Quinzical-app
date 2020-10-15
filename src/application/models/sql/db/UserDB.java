package application.models.sql.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.models.sql.SQLConnection;
import application.models.sql.data.UserData;

/**
 * UserDB used to handle Users SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class UserDB {
    /**
     * Used to insert a user to database
     * 
     * @param name
     * @throws SQLException
     * @return id
     */
    public int insert(final String name) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO users(name,completed,game_session_id,international_score) VALUES(?,0,0,0)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        SQLConnection.closeConnection(conn);
        return id;
    }

    /**
     * Used to get a user by userID
     * 
     * @param id
     * @return User
     * @throws SQLException
     */
    public UserData query(final int id) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name, completed, game_session_id, international_score FROM users WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        UserData user = null;
        if (rs.next()) {
            user = new UserData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("completed"),
                    rs.getInt("game_session_id"), rs.getInt("international_score"));
        }

        SQLConnection.closeConnection(conn);
        return user;
    }

    /**
     * Used to get all users
     * 
     * @return List of User
     * @throws SQLException
     */
    public List<UserData> query() throws SQLException {
        List<UserData> users = new ArrayList<UserData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name, completed, game_session_id, international_score FROM users";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            users.add(new UserData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("completed"),
                    rs.getInt("game_session_id"), rs.getInt("international_score")));
        }

        return users;
    }

    /**
     * Used to update completed for user
     * 
     * @param id
     * @param completed
     * @throws SQLException
     */
    public void setCompleted(final int id, final boolean completed) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "UPDATE users SET completed=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setBoolean(1, completed);
        pstmt.setInt(2, id);
        pstmt.execute();
    }

    /**
     * Used to update game_session_id
     * 
     * @param id
     * @param session
     * @throws SQLException
     */
    public void setGameSessionID(final int id, final int session) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "UPDATE users SET game_session_id=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, session);
        pstmt.setInt(2, id);
        pstmt.execute();
    }

    /**
     * Used to update international score
     * 
     * @param id
     * @param score
     * @throws SQLException
     */
    public void setInternationalScore(final int id, final int score) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "UPDATE users SET international_score=? WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, score);
        pstmt.setInt(2, id);
        pstmt.execute();
    }

    /**
     * Used to check if user exists
     * 
     * @param username
     * @return UserData
     * @throws SQLException
     */
    public UserData checkUser(final String username) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT * FROM users WHERE name=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();

        UserData user = null;
        if (rs.next()) {
            user = new UserData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("completed"),
                    rs.getInt("game_session_id"), rs.getInt("international_score"));
        }

        pstmt.close();
        rs.close();
        SQLConnection.closeConnection(conn);
        return user;
    }
}
