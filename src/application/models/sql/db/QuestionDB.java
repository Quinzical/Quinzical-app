package application.models.sql.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.models.sql.SQLConnection;
import application.models.sql.data.QuestionData;

/**
 * QuestionDB used to handle Questions in SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionDB {
    /**
     * Used to insert a question to database
     * 
     * @param categoryID
     * @param question
     * @param qualifier
     * @throws SQLException
     * @return id
     */
    public int insert(final int categoryID, final String question, final String qualifier) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO questions(category_id, question, qualifier) VALUES(?,?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, categoryID);
        pstmt.setString(2, question);
        pstmt.setString(3, qualifier);
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        SQLConnection.closeConnection(conn);
        return id;
    }

    /**
     * Used to get a question by questionID
     * 
     * @param id
     * @return question
     * @throws SQLException
     */
    public QuestionData query(final int id) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, category_id, question, qualifier FROM questions WHERE id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        QuestionData question = null;
        if (rs.next()) {
            question = new QuestionData(rs.getInt("id"), rs.getInt("category_id"), rs.getString("question"),
                    rs.getString("qualifier"));
        }

        SQLConnection.closeConnection(conn);
        return question;
    }

    /**
     * Used to get a random question by categoryID
     * 
     * @param categoryID
     * @return question
     * @throws SQLException
     */
    public QuestionData randomQuestion(final int categoryID) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, category_id, question, qualifier FROM questions WHERE category_id=? ORDER BY RANDOM() LIMIT 1";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, categoryID);
        ResultSet rs = pstmt.executeQuery();

        QuestionData question = null;
        if (rs.next()) {
            question = new QuestionData(rs.getInt("id"), rs.getInt("category_id"), rs.getString("question"),
                    rs.getString("qualifier"));
        }

        SQLConnection.closeConnection(conn);
        return question;
    }

    /**
     * Used to get all questions
     * 
     * @return List of questions
     */
    public List<QuestionData> query() throws SQLException {
        List<QuestionData> questions = new ArrayList<QuestionData>();

        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT id, name FROM questions";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            questions.add(new QuestionData(rs.getInt("id"), rs.getInt("category_id"), rs.getString("question"),
                    rs.getString("qualifier")));
        }

        return questions;
    }

    /**
     * Used to insert a answer to a question
     * 
     * @param questionID
     * @param answer
     * @return id
     * @throws SQLException
     */
    public int insertAnswer(final int questionID, final String answer) throws SQLException {
        int id = 0;
        Connection conn = SQLConnection.createConnection();
        String sql = "INSERT INTO answers(question_id, answer) VALUES(?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, questionID);
        pstmt.setString(2, answer);
        pstmt.executeUpdate();

        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            id = rs.getInt(1);
        }

        SQLConnection.closeConnection(conn);
        return id;
    }

    /**
     * Used to get answers by questionID
     * 
     * @param questionID
     * @return answers
     * @throws SQLException
     */
    public List<String> getAnswers(final int questionID) throws SQLException {
        Connection conn = SQLConnection.createConnection();
        String sql = "SELECT answer FROM answers WHERE question_id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, questionID);
        ResultSet rs = pstmt.executeQuery();

        List<String> answers = new ArrayList<String>();
        while (rs.next()) {
            answers.add(rs.getString("answer"));
        }

        SQLConnection.closeConnection(conn);
        return answers;
    }
}
