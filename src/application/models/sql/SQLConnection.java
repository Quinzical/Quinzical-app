package application.models.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import application.helper.FileHelper;
import application.models.helper.FileHashCheck;
import application.models.helper.QuestionHelper;
import application.models.sql.db.CategoryDB;
import application.models.sql.db.QuestionDB;

/**
 * This class is the SQLConnection singleton
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SQLConnection {
    private static SQLConnection _instance = new SQLConnection();

    private SQLConnection() {
        String gameData = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data";
        FileHelper.makeDirectory(gameData);
        try {
            FileHashCheck checker = new FileHashCheck();
            if (!FileHelper.checkIfFileExist(FileHelper.SQLITE_DB_FILE)) {
                createTables();
                loadQuestionDataFile();
            } else if (checker.checkChange()) {
                System.out.println("Change has been detected, Reloading questions");
                reloadQuestions();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return SQLConnection
     */
    public static SQLConnection getInstance() {
        return _instance;
    }

    private void createTables() {
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, unlock BOOLEAN, game_session_id INTEGER, international_score INTEGER);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS game_sessions (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, categories VARCHAR, questions VARCHAR, score INTEGER);");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS attempts (id INTEGER PRIMARY KEY AUTOINCREMENT, game_session_id INTEGER, category_id INTEGER, question_id INTEGER, score INTEGER, correct BOOLEAN);");
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
     * WARNING Truncates categories and questions
     */
    private void reloadQuestions() {
        Connection connection = null;
        try {
            connection = createConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM categories;");
            statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='categories';");
            statement.executeUpdate("DELETE FROM questions;");
            statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='questions';");
            loadQuestionDataFile();
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    /**
     * Used to load quinzical.txt to SQL DB
     */
    private void loadQuestionDataFile() throws SQLException, IOException {
        String quinzical = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical" + ".txt";
        File quinzicalFile = new File(quinzical);
        if (quinzicalFile.isFile()) {
            BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));

            String line = null;
            int categoryID = 0;
            CategoryDB categoryDB = new CategoryDB();
            QuestionDB questionDB = new QuestionDB();

            while ((line = in.readLine()) != null) {
                // Replace spaces with hypens to name the files
                if (categoryID == 0) {
                    categoryID = categoryDB.insert(line);
                } else if (line.isEmpty()) {
                    categoryID = 0;
                } else {
                    String[] separated = line.split("\\\\");
                    QuestionHelper helper = QuestionHelper.getInstance();
                    int questionID = questionDB.insert(categoryID, separated[0], helper.retrievePrompt(separated[1]));
                    String[] answers = separated[1].substring(separated[1].lastIndexOf(")") + 2).split("/");
                    for (String answer : answers) {
                        questionDB.insertAnswer(questionID, answer);
                    }
                }
            }
            in.close();
        }
    }

    /**
     * Used to create a SQL connection
     * 
     * @return Connection
     * @throws SQLException
     */
    public static Connection createConnection() throws SQLException {
        String connString = String.format("jdbc:sqlite:%s", FileHelper.SQLITE_DB_FILE);
        return DriverManager.getConnection(connString);
    }

    /**
     * Used to close a SQL connection
     * 
     * @param connection
     */
    public static void closeConnection(final Connection connection) {
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
