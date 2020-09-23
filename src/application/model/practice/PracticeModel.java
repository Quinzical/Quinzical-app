package application.model.practice;

import java.util.List;

import application.model.helper.Category;

/**
 * This class is used to delegate tasks to different classes who carry out tasks for the function of the practice module based on what the user wants to do.
 *  
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public class PracticeModel {
	
	private static PracticeModel _instance;
	
	private PracticeFiles _practiceFiles = new PracticeFiles();
	private PracticeQuestionQuery _questionQuery = new PracticeQuestionQuery();
	
	private PracticeModel() {
	}
	
	/**
	 * Used to return the single instance of this class.
	 * 
	 * @return PracticeModel
	 */
	public static PracticeModel getInstance() {
		if (_instance == null) {
			_instance = new PracticeModel();
		}
		return _instance;
	}
	
	/**
	 * Used to set up the practice module by calling the correct functions from supplementary classes.
	 */
	public void setUpPracticeModule() {
		_practiceFiles.setUpPracticeModule();
		_practiceFiles.copyCategories();
	}
	
	/**
	 * Used to get a random practice question based on the chosen category. 
	 * 
	 * @param numberOfAttempts
	 * @param category the category chosen by the user
	 * @return String  the question
	 */
	public String getPracticeQuestion(String category, int numberOfAttempts) {
		return _questionQuery.retrieveQuestion(category, numberOfAttempts);
	}

	/**
	 * Used to check whether the users answer for the current question is correct or not. 
	 * 
	 * @param userAnswer 
	 * @return String the message based on how the user answers
	 */
	public String checkPracticeAnswer(String userAnswer, int numberOfAttempts) {
		return _questionQuery.checkPracticeAnswer(userAnswer, numberOfAttempts);
	}
	
	/**
	 * Used to send the clue for the current question to the user. This is only to be used if they are on their third attempt.  
	 * 
	 * @return String the clue for the user
	 */
	public String getClue() {
		return _questionQuery.getClueFromQuestion();
	}
	
	/**
	 * Used to deliver a list of categories to the required controllers
	 * 
	 * @return
	 */
	public List<Category> getPracticeCategories() {
		return _practiceFiles.getCategories();
	}
}
