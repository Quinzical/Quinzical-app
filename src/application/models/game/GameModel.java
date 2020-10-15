package application.models.game;

import java.util.List;

import application.controllers.helper.GameStateData;
import application.models.helper.Category;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the games module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public interface GameModel {

    /**
     * Used to set up the game module by calling the correct functions from
     * supplementary classes.
     */
    void setUpGameModule();

    /**
     * Get score from file to display to the user.
     * 
     * @return score
     */
    int getScore();

    /**
     * Used to get a game question based on the chosen category and the value of the
     * question.
     * 
     * @param category the category chosen by the user
     * @return String the question to be displayed to the user
     */
    String getGameQuestion(Category category);

    /**
     * Used to get number of questions left in a category
     * 
     * @param category the category chosen
     * @return int the number of questions left
     */
    int getCategoriesQuestionNumber(Category category);

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @param questionValue
     * @return boolean true if correct, false if incorrect
     */
    boolean checkGameAnswer(String userAnswer, String questionValue);

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current game categories
     */
    List<Category> getGameCategories();

    /**
     * Used to get detailed Game State only supports SQL
     * 
     * @return GameStateData
     */
    GameStateData getGameStateData();

    /**
     * Used to reset the game for the current user.
     */
    void resetGameModule();

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    String getGameAnswer();

    /**
     * Gets the prompt from a question i.e. "What is".
     * 
     * @return String the prompt
     */
    String getPrompt();

    /**
     * Deletes question from file.
     * 
     * @param questionValue
     */
    void deleteQuestion(String questionValue);

    /**
     * Used to check if there are remaining questions in the game module.
     * 
     * @return boolean
     */
    boolean remainingQuestions();
}
