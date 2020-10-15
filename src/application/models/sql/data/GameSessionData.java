package application.models.sql.data;

import java.util.Arrays;

/**
 * GameSessionData used to store Game Session data from SQL Database
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameSessionData {
    private int _id;
    private int _userID;
    private String[] _categories;
    private String[] _questions;
    private int _score;

    /**
     * Create GameSession DB Object
     * 
     * @param id
     * @param userID
     * @param categories
     * @param questions
     * @param score
     */
    public GameSessionData(final int id, final int userID, final String categories, final String questions,
            final int score) {
        _id = id;
        _userID = userID;
        _categories = categories.split(",");
        _questions = questions.split(",");
        _score = score;
    }

    /**
     * @return int return the id
     */
    public int getID() {
        return _id;
    }

    /**
     * @return int return the userID
     */
    public int getUserID() {
        return _userID;
    }

    /**
     * @return String return the categories
     */
    public String[] getCategories() {
        return _categories;
    }

    /**
     * @return String return the categories
     */
    public String getCategoriesString() {
        return String.join(",", _categories);
    }

    /**
     * @return String return the question
     */
    public String[] getQuestions() {
        return _questions;
    }

    /**
     * @return String return the categories
     */
    public String getQuestionsString() {
        return String.join(",", _questions);
    }

    /**
     * @param questions Used to set Questions
     */
    public void setQuestions(final String[] questions) {
        _questions = questions;
    }

    /**
     * @return int return the score
     */
    public int getScore() {
        return _score;
    }

    /**
     * Used to set score
     * @param score
     */
    public void setScore(final int score) {
        _score = score;
    }

    /**
     * Used to get number of questions left in a category
     * 
     * @param category
     * @return number of questions left
     */
    public int getCategoriesQuestionNumber(final int category) {
        int index = Arrays.asList(_categories).indexOf(String.valueOf(category));
        return 5 - Integer.valueOf(_questions[index]);
    }

    /**
     * Used to update question state by category
     * 
     * @param category
     * @param correct
     */
    public void setCategoriesQuestionNumber(final int category, final boolean correct) {
        int index = Arrays.asList(_categories).indexOf(String.valueOf(category));
        _questions[index] = String.valueOf(Integer.valueOf(_questions[index]) + 1);
    }
}
