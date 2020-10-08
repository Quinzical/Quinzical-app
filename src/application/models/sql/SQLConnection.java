package application.models.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is the SQLConnection singleton
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SQLConnection {
    private static SQLConnection _instance;

    private static final String SQLITE_DB_FILE = "./data/data.db";

    private SQLConnection() {
        createTables();
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return SQLConnection
     */
    public static SQLConnection getInstance() {
        if (_instance == null) {
            _instance = new SQLConnection();
        }
        return _instance;
    }

    private void createTables() {
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, international BOOLEAN);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS game_sessions (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, categories VARCHAR, score INTEGER);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS results (id INTEGER PRIMARY KEY AUTOINCREMENT, game_session_id INTEGER, category_id INTEGER, question_id INTEGER, score INTEGER, correct BOOLEAN);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS questions (id INTEGER PRIMARY KEY AUTOINCREMENT, category_id INTEGER, question VARCHAR, qualifier VARCHAR);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS answers (id INTEGER PRIMARY KEY AUTOINCREMENT, question_id INTEGER, answer VARCHAR);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS leaderboard (id INTEGER PRIMARY KEY, game_session_id INTEGER);");
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Used to load quinzical.txt to SQL DB
     */
    private void loadDataFile() {
        // TODO
    }

    /**
     * Used to create a SQL connection
     * 
     * @return Connection
     * @throws SQLException
     */
    public Connection createConnection() throws SQLException {
        String connString = String.format("jdbc:sqlite:%s", SQLITE_DB_FILE);
        return DriverManager.getConnection(connString);
    }

    /**
     * Used to close a SQL connection
     * 
     * @param connection
     */
    public void closeConnection(final Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }
}
