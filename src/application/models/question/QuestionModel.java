package application.models.question;

import application.models.game.GameModel;
import application.models.helper.Category;
import application.models.practice.PracticeModel;

/**
 * This class is used to delegate tasks to different classes who carry out tasks for the function of the practice module based on what the user wants to do.
 *  
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public class QuestionModel {

	private static QuestionModel _instance;
	
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
	 * Used to indicate to the question model whether the question is being asked in the game or practice module.
	 * 
	 * @param practiceOrNot value should be true if practice module, false if games module
	 */
	public void setPractice(boolean practiceOrNot) {
		_practice = practiceOrNot;
	}
	
	/**
	 * Used to set the category for both the games and practice module.
	 * 
	 * @param category
	 */
	public void setCategory(Category category) {
		_category = category;
	}
	
	/**
	 * Used to set the number of attempts for the practice module.
	 * 
	 * @param numberOfAttempts
	 */
	public void setNumberOfAttempts(int numberOfAttempts) {
		_numberOfAttempts = numberOfAttempts;
	}
	
	/**
	 * Used to set the current question value for use in the games module.
	 * 
	 * @param value
	 */
	public void setQuestionValue(String value) {
		_questionValue = value;
	}
	
	/**
	 * Returns a question.
	 * 
	 * @return String the question to be displayed to the user
	 */
	public String getQuestion() {
		if (_practice) {
			PracticeModel practiceModel = PracticeModel.getInstance();
			return practiceModel.getPracticeQuestion(_category, _numberOfAttempts);
		} else {
			GameModel gameModel = GameModel.getInstance();
			return gameModel.getGameQuestion(_category, _questionValue);
		}
	}
	
	/**
	 * Used to check the answers for the games or question model, based on what the user module is playing in.
	 * 
	 * @param userAnswer
	 * @return boolean true if the user gets it correct, false if they get it incorrect
	 */
	public boolean checkAnswer(String userAnswer) {
		if (_practice) {
			PracticeModel practiceModel = PracticeModel.getInstance();
			return practiceModel.checkPracticeAnswer(userAnswer, _numberOfAttempts);
		} else {
			GameModel gameModel = GameModel.getInstance();
			return gameModel.checkGameAnswer(userAnswer, _questionValue);
		}
	}
	
	/**
	 * Used to get a clue for the current question if the user is on their third attempt.
	 * 
	 * @return String the clue to be displayed to the user
	 */
	public String getClue() {
		if (_practice && _numberOfAttempts == 2) {
			PracticeModel practiceModel = PracticeModel.getInstance();
			System.out.println(practiceModel.getClue());
			return practiceModel.getClue();
		}
		return null;
	}
}
