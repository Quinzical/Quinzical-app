package application.models.question;

import application.models.game.sql.GameModelSQL;
import application.models.helper.Category;
import application.models.practice.PracticeModel;
import application.models.practice.sql.PracticeModelSQL;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the practice module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class QuestionModel {

    private static QuestionModel _instance;

    private PracticeModel _practiceModel = PracticeModelSQL.getInstance();
    private GameModelSQL _gameModel = GameModelSQL.getInstance();

    private boolean _practice;
    private Category _category;
    private String _questionValue;
    private int _numberOfAttempts;

    private QuestionModel() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return QuestionModel
     */
    public static QuestionModel getInstance() {
        if (_instance == null) {
            _instance = new QuestionModel();
        }
        return _instance;
    }

    /**
     * Used to indicate to the question model whether the question is being asked in
     * the game or practice module.
     * 
     * @return practice
     */
    public boolean getPractice() {
        return _practice;
    }

    /**
     * Used to indicate to the question model whether the question is being asked in
     * the game or practice module.
     * 
     * @param practiceOrNot value should be true if practice module, false if games
     *                      module
     */
    public void setPractice(final boolean practiceOrNot) {
        _practice = practiceOrNot;
    }

    /**
     * Used to get the category for both the games and practice module.
     * 
     * @return category
     */
    public Category getCategory() {
        return _category;
    }

    /**
     * Used to set the category for both the games and practice module.
     * 
     * @param category
     */
    public void setCategory(final Category category) {
        _category = category;
    }

    /**
     * Used to set the number of attempts for the practice module.
     * 
     * @param numberOfAttempts
     */
    public void setNumberOfAttempts(final int numberOfAttempts) {
        _numberOfAttempts = numberOfAttempts;
    }

    /**
     * Used to get the current question value for use in games module.
     * 
     * @return question value
     */
    public String getQuestionValue() {
        return _questionValue;
    }

    /**
     * Used to set the current question value for use in the games module.
     * 
     * @param value
     */
    public void setQuestionValue(final String value) {
        _questionValue = value;
    }

    /**
     * Used to get question prompt
     * 
     * @return prompt
     */
    public String getQuestionPrompt() {
        if (_practice) {
            return _practiceModel.getPrompt();
        } else {
            return _gameModel.getPrompt();
        }
    }

    /**
     * Returns a question.
     * 
     * @return String the question to be displayed to the user
     */
    public String getQuestion() {
        if (_practice) {
            return _practiceModel.getPracticeQuestion(_category);
        } else {
            return _gameModel.getGameQuestion(_category);
        }
    }

    /**
     * Used to check the answers for the games or question model, based on what the
     * user module is playing in.
     * 
     * @param userAnswer
     * @return boolean true if the user gets it correct, false if they get it
     *         incorrect
     */
    public boolean checkAnswer(final String userAnswer) {
        if (_practice) {
            return _practiceModel.checkPracticeAnswer(userAnswer);
        } else {
            return _gameModel.checkGameAnswer(userAnswer, _questionValue);
        }
    }

    /**
     * Used to get a clue for the current question if the user is on their third
     * attempt.
     * 
     * @return String the clue to be displayed to the user
     */
    public String getClue() {
        if (_practice && _numberOfAttempts >= 2) {
            return _practiceModel.getClue();
        }
        return null;
    }

    /**
     * Returns the correct current answer.
     * 
     * @return String the correct answer for the current question.
     */
    public String getCorrectAnswer() {
        if (_practice) {
            return _practiceModel.getPracticeAnswer();
        } else {
            return _gameModel.getGameAnswer();
        }
    }
}
