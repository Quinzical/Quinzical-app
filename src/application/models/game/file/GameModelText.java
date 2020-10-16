package application.models.game.file;

import java.util.List;

import application.controllers.helper.GameStateData;
import application.helper.FileHelper;
import application.models.game.GameModel;
import application.models.helper.Category;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the games module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class GameModelText implements GameModel {

    private static GameModelText _instance;

    private GameFiles _gameFiles = new GameFiles();
    private GameQuestionQuery _questionQuery = new GameQuestionQuery();

    private ScoreTracker _score = new ScoreTracker();

    private GameModelText() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return GameModel
     */
    public static GameModelText getInstance() {
        if (_instance == null) {
            _instance = new GameModelText();
        }
        return _instance;
    }

    /**
     * Used to set up the game module by calling the correct functions from
     * supplementary classes.
     */
    public void setUpGameModule() {
        // Set up files for game module
        FileHelper.setUpGame();
        _gameFiles.setUpGameModule();
        _gameFiles.randomiseCategories();

        // Set up score tracker
        _score.setUpScore();
    }

    /**
     * Get score from file to display to the user.
     * 
     * @return score
     */
    public int getScore() {
        return _score.getCurrentScore();
    }

    /**
     * Used to get a game question based on the chosen category and the value of the
     * question.
     * 
     * @param category the category chosen by the user
     * @return String the question to be displayed to the user
     */
    public String getGameQuestion(final Category category) {
        return _questionQuery.retrieveQuestion(category);
    }

    /**
     * Used to get number of questions left in a category
     * 
     * @param category the category chosen
     * @return int the number of questions left
     */
    public int getCategoriesQuestionNumber(final Category category) {
        return _gameFiles.getCategoriesQuestionNum(category);
    }

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @param questionValue
     * @return boolean true if correct, false if incorrect
     */
    public boolean checkGameAnswer(final String userAnswer, final String questionValue) {
        boolean correct = _questionQuery.checkGameAnswer(userAnswer);
        if (correct) {
            _score.addWinnings(questionValue);
        }
        return correct;
    }

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current game categories
     */
    public List<Category> getGameCategories() {
        return _gameFiles.getCategories();
    }

    /**
     * Used to get detailed Game State only supports SQL
     * 
     * @return GameStateData
     */
    public GameStateData getGameStateData() {
        return new GameStateData();
    }

    /**
     * Used to reset the game for the current user.
     */
    public void resetGameModule() {
        _gameFiles.resetGame();
        _score.setUpScore();
    }

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    public String getGameAnswer() {
        return _questionQuery.retrieveAnswer();
    }

    /**
     * Gets the prompt from a question i.e. "What is".
     * 
     * @return String the prompt
     */
    public String getPrompt() {
        return _questionQuery.getPrompt();
    }

    /**
     * Deletes question from file.
     * 
     * @param questionValue
     */
    public void deleteQuestion(final String questionValue) {
        _questionQuery.deleteQuestionFromFile();
    }

    /**
     * Used to check if there are remaining questions in the game module.
     * 
     * @return boolean
     */
    public boolean remainingQuestions() {
        return _gameFiles.remainingQuestions();
    }

    /**
     * Used to check if two category is completed
     * 
     * @return boolean
     */
    public boolean checkInternational() {
        return (_gameFiles.remainingCategories() <= 3);
    }
}
