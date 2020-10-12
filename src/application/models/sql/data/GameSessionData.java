package application.models.sql.data;

import java.util.Arrays;

public class GameSessionData {
    private int _id;
    private int _userID;
    private String _categories;
    private String _questions;
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
        _categories = categories;
        _questions = questions;
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
    public String getCategories() {
        return _categories;
    }

    /**
     * @return String return the question
     */
    public String getQuestions() {
        return _questions;
    }

    /**
     * @return int return the score
     */
    public int getScore() {
        return _score;
    }

    /**
     * Used to get number of questions left in a category
     * 
     * @param category
     * @return number of questions left
     */
    public int getCategoriesQuestionNumber(final int category) {
        int index = Arrays.asList(_categories.split(",")).indexOf(String.valueOf(category));
        return 5 - Integer.valueOf(_questions.split(",")[index]);
    }

}
