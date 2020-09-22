package application.model.game;

import java.io.File;
import java.util.List;

import application.model.helper.FileHelper;
import application.model.helper.QuestionHelper;

/**
 * This class is used to retrieve questions from their relevant files for the practice module.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameQuestionQuery {
	
	String _currentQuestion = null;
	String _currentAnswer = null;
	
	boolean _correctAnswer = false;

	public GameQuestionQuery() {
	}

	/**
	 * Return the question to the model. 
	 * 
	 * @param category
	 * @param questionValue
	 * @return String the question to be returned
	 */
	public String retrieveQuestion(String category, String questionValue) {
		//Replace spaces from category to hyphen if not already done
		category = category.replace(' ', '-');
		
		String questionStr = GameFiles.getUserCategories() + FileHelper.FILESEPARATOR + category + ".txt";
		File questionFile = new File(questionStr);
		
		return getQuestionFromFile(questionFile, questionValue);
	}

	/**
	 * Get desired question from the users files.
	 * 
	 * @param numberOfQuestions the number of questions in a given category
	 * @param questionFile      the file which contains the questions of a given category
	 */
	private String getQuestionFromFile(File questionFile, String questionValue) {
		int lineNumber = Integer.valueOf(questionValue) / 100;
		List<String> questionAndAnswer = FileHelper.getLineFromFile(questionFile, lineNumber);
		setQuestionAndAnswer(questionAndAnswer);
		return _currentQuestion;
	}

	/**
	 * Used to set question and answer fields of this class.
	 * 
	 * @param questionAndAnswer a list where the first element is the question and the second element is the answer
	 */
	private void setQuestionAndAnswer(List<String> questionAndAnswer) {
		_currentQuestion = questionAndAnswer.get(0);
		_currentAnswer = questionAndAnswer.get(1);
		
		//Trim leading space on answer
		_currentAnswer = _currentAnswer.trim();
		System.out.println(_currentQuestion);
		System.out.println(_currentAnswer);	
	}
	
	/**
	 * Used to check the correctness of the users answer. 
	 * 
	 * @param userAnswer the answer supplied by the user
	 * @return String    a string based on how many attempts the user has had and the answer they supply
	 */
	public String checkGameAnswer(String userAnswer) {
		boolean correct = QuestionHelper.checkQuestion(userAnswer, _currentAnswer);
		_correctAnswer = correct;
		if (correct) {
			return "Success!";
		} else {
			return "Incorrect!\n" + "The correct answer for the question " + _currentQuestion + "was:\n" + _currentAnswer;
		}
	}

	/**
	 * Used to tell if the user got it correct or not.
	 * 
	 * @return boolean true if correct, false if incorrect
	 */
	public boolean wonOrNot() {
		return _correctAnswer;
	}
}
