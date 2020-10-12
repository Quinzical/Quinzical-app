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
        String sql = "INSERT INTO users(name,international) VALUES(?,0)";

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
        String sql = "SELECT id, name, international FROM users WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        UserData user = null;
        if (rs.next()) {
            user = new UserData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("international"));
        }

        SQLConnection.closeConnection(conn);
        return user;
    }

    /**
     * Used to get all users
     * 
     * @return List of User
     */
    public List<UserData> query() throws SQLException {
        List<UserData> users = new ArrayList<UserData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name, international FROM users";

        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()) {
            users.add(new UserData(rs.getInt("id"), rs.getString("name"), rs.getBoolean("international")));
        }

        return users;
    }
}