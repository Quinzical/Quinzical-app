package application.models.practice;

import java.io.File;
import java.util.List;

import application.helper.FileHelper;
import application.models.helper.Category;
import application.models.helper.QuestionHelper;

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
	 * @return String the question to be displayed to the user
	 */
	public String retrieveQuestion(Category category) {
		//Replace spaces from category to hyphen if not already done
		String categoryName = category.toString().replace(' ', '-');

		String questionStr = PracticeFiles.getCategoryFolder() + FileHelper.FILE_SEPARATOR + categoryName + ".txt";
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
	 * Used to check the correctness of the users answer. 
	 * 
	 * @param userAnswer the answer supplied by the user
	 * @return boolean true if correct, false if incorrect
	 */
	public boolean checkPracticeAnswer(String userAnswer) {
		QuestionHelper helper = QuestionHelper.getInstance();
		return helper.checkQuestion(userAnswer, _currentAnswer);
		
	}

	/**
	 * Returns the clue for the current question.
	 *
	 * @return String the clue
	 */
	public String getClueFromQuestion() {
		return Character.toString(QuestionHelper.trimAnswer(_currentAnswer).charAt(0));
	}
	
	/**
	 * Used to get the correct answer for the current question. 
	 * 
	 * @return String the current answer
	 */
	public String retrieveAnswer() {
		return _currentAnswer;
	}

	public String getPrompt() {
		if(_currentAnswer != null) {
			QuestionHelper helper = QuestionHelper.getInstance();
			return helper.retrievePrompt(_currentAnswer);
		}
		return null;
	}
}
