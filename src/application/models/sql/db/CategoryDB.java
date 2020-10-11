package application.models.sql.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.models.sql.SQLConnection;
import application.models.sql.data.CategoryData;

public class CategoryDB {
    /**
     * Used to insert a category to database
     * 
     * @param name
     * @throws SQLException
     * @return id
     */
    public int insert(final String name) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO categories(name) VALUES(?)";

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
     * Used to get a category by categoryID
     * 
     * @param id
     * @return category
     * @throws SQLException
     */
    public CategoryData query(final int id) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name FROM categories WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        CategoryData category = null;
        if (rs.next()) {
            category = new CategoryData(rs.getInt("id"), rs.getString("name"));
        }

        SQLConnection.closeConnection(conn);
        return category;
    }

    /**
     * Used to get all categories
     * 
     * @return List of category
     */
    public List<CategoryData> query() throws SQLException {
        List<CategoryData> categories = new ArrayList<CategoryData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name FROM categories";

        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()) {
            categories.add(new CategoryData(rs.getInt("id"), rs.getString("name")));
        }

        return categories;
    }
}
