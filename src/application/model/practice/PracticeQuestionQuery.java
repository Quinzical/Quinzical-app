package application.model.practice;

import java.io.File;
import java.util.List;

import application.model.helper.FileHelper;

/**
 * This class is used to retrieve questions from their relevant files for the practice module.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class PracticeQuestionQuery {

	String _currentQuestion = null;
	String _currentAnswer = null;

	public PracticeQuestionQuery() {
	}

	/**
	 * Used to retrieve a random question based on the category chosen by the user. 
	 * 
	 * @param category
	 * @param numberOfAttempts the number of attempts the user has had on this particular question
	 * @return String the question to be displayed to the user
	 */
	public String retrieveQuestion(String category, int numberOfAttempts) {
		//Replace spaces from category to hyphen if not already done
		category = category.replace(' ', '-');

		String questionStr = PracticeFiles._categoryFolder + FileHelper.FILESEPARATOR + category + ".txt";
		File questionFile = new File(questionStr);

		return generateRandomQuestion(FileHelper.countLinesinFile(questionFile), questionFile);
	}

	/**
	 * Generate a random question based on the chosen category of the user.
	 * 
	 * @param numberOfQuestions the number of questions in a given category
	 * @param questionFile      the file which contains the questions of a given category
	 */
	private String generateRandomQuestion(int numberOfQuestions, File questionFile) {
		int randomNumber = 1 + (int)(Math.random() * ((numberOfQuestions - 1) + 1));
		List<String> questionAndAnswer = FileHelper.getLineFromFile(questionFile, randomNumber);
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
	}

	/**
	 * used to check the correctness of the users answer. 
	 * 
	 * @param userAnswer the answer supplied by the user
	 * @return String    a string based on how many attempts the user has had and the answer they supply
	 */
	public String checkAnswer(String userAnswer) {
		if (userAnswer.toLowerCase().contains(_currentAnswer.toLowerCase())) {
			return "Correct!";
		}
		return "Incorrect, the correct answer was: " + _currentAnswer;
	}
}
