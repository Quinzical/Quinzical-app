package application.models.game.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import application.controllers.helper.GameStateData;
import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.api.LeaderboardModel;
import application.models.game.GameModel;
import application.models.helper.Category;
import application.models.helper.QuestionHelper;
import application.models.login.LoginModel;
import application.models.sql.data.CategoryData;
import application.models.sql.data.QuestionData;
import application.models.sql.db.CategoryDB;
import application.models.sql.db.GameSessionDB;
import application.models.sql.db.QuestionDB;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the games module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class GameModelSQL implements GameModel {
    private static GameModelSQL _instance;

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private LoginModel _login = LoginModel.getInstance();
    private GameSessionDB _gameSessionDB = new GameSessionDB();
    private QuestionDB _questionDB = new QuestionDB();
    private CategoryDB _categoryDB = new CategoryDB();

    private int _questionID;
    private int _categoryID;
    private int _score;

    private GameModelSQL() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return GameModel
     */
    public static GameModelSQL getInstance() {
        if (_instance == null) {
            _instance = new GameModelSQL();
        }
        return _instance;
    }

    /**
     * Get score from file to display to the user.
     * 
     * @return score
     */
    public int getScore() {
        try {
            return _gameSessionDB.query(_login.getGameSessionID()).getScore();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Used to get a game question based on the chosen category and the value of the
     * question.
     * 
     * @param category the category chosen by the user
     * @return String the question to be displayed to the user
     */
    public String getGameQuestion(final Category category) {
        try {
            QuestionData question = _questionDB.randomQuestion(category.getCategoryID(), _login.getGameSessionID());
            _questionID = question.getID();
            _categoryID = category.getCategoryID();
            return question.getQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to get number of questions left in a category
     * 
     * @param category the category chosen
     * @return int the number of questions left
     */
    public int getCategoriesQuestionNumber(final Category category) {
        try {
            return _gameSessionDB.query(_login.getGameSessionID())
                    .getCategoriesQuestionNumber(category.getCategoryID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
        QuestionHelper helper = QuestionHelper.getInstance();
        _score = Integer.valueOf(questionValue);
        boolean correct = false;
        try {
            List<String> answers = _questionDB.getAnswers(_questionID);
            correct = helper.compareAnswers(answers, helper.removePrompt(userAnswer));
            _gameSessionDB.insertAttempt(_login.getGameSessionID(), _categoryID, _questionID, _score, correct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return correct;
    }

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current game categories
     */
    public List<Category> getGameCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            int[] ids = new int[5];
            _login.getGameSessionID();
            String[] stringID = _gameSessionDB.query(_login.getGameSessionID()).getCategories();
            for (int i = 0; i < stringID.length; i++) {
                ids[i] = Integer.valueOf(stringID[i]);
            }
            HashMap<Integer, CategoryData> data = _categoryDB.query(ids);
            for (int i = 0; i < stringID.length; i++) {
                ids[i] = Integer.valueOf(stringID[i]);
                categories.add(new Category(data.get(ids[i]).getName(), data.get(ids[i]).getID()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Used to get detailed Game State only supports SQL
     * 
     * @return GameStateData
     */
    public GameStateData getGameStateData() {
        try {
            String[] categories = _gameSessionDB.query(_login.getGameSessionID()).getCategories();
            return _gameSessionDB.getGameState(convertArrayToInt(categories), _login.getGameSessionID());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to reset the game for the current user.
     */
    public void resetGameModule() {
        LeaderboardModel leaderboard = LeaderboardModel.getInstance();
        leaderboard.postLeaderboard();
        _login.setGameSessionID(0);
        _login.disableInternational();
        _sceneManager.unloadScene();
        _sceneManager.switchScene(Scenes.CATEGORY_CHOOSER);
    }

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    public String getGameAnswer() {
        try {
            List<String> question = _questionDB.getAnswers(_questionID);
            return question.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Gets the prompt from a question i.e. "What is".
     * 
     * @return String the prompt
     */
    public String getPrompt() {
        try {
            QuestionData question = _questionDB.query(_questionID);
            return question.getQualifier();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Deletes question from file.
     * 
     * @param questionValue
     */
    public void deleteQuestion(final String questionValue) {
        _score = Integer.valueOf(questionValue);
        try {
            _gameSessionDB.insertAttempt(_login.getGameSessionID(), _categoryID, _questionID, _score, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to check if there are remaining questions in the game module.
     * 
     * @return boolean
     */
    public boolean remainingQuestions() {
        try {
            return !Arrays.equals(_gameSessionDB.query(_login.getGameSessionID()).getQuestions(),
                    new String[] { "5", "5", "5", "5", "5" });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Used to convert string[] to int[]
     * 
     * @param array string
     * @return int array
     */
    private int[] convertArrayToInt(final String[] array) {
        int[] ids = new int[5];
        for (int i = 0; i < array.length; i++) {
            ids[i] = Integer.valueOf(array[i]);
        }
        return ids;
    }

    /**
     * Used to check if two category is completed
     * 
     * @return boolean if two category is completed
     */
    public boolean checkInternational() {
        int count = 0;
        try {
            String[] questions = _gameSessionDB.query(_login.getGameSessionID()).getQuestions();
            for (String question : questions) {
                if (question.equals("5")) {
                    if (count >= 1) {
                        return true;
                    }
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
