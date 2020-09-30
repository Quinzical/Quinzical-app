package application.models.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
public class GameQuestionQuery {

	private String _currentQuestion = null;
	private String _currentAnswer = null;
	private int _lineNumber;

	private File _questionFile;

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
	public String retrieveQuestion(Category category) {		
		//Replace spaces from category to hyphen if not already done
		String categoryName = category.getFilename();
		
		String questionStr = GameFiles.getUserCategories() + FileHelper.FILE_SEPARATOR + categoryName;
		_questionFile = new File(questionStr);

		return getQuestionFromFile(_questionFile);
	}

	/**
	 * Get desired question from the users files.
	 * 
	 * @param numberOfQuestions the number of questions in a given category
	 * @param questionFile      the file which contains the questions of a given category
	 */
	private String getQuestionFromFile(File questionFile) {
		_lineNumber = 1;
		List<String> questionAndAnswer = FileHelper.getLineFromFile(questionFile, _lineNumber);
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
	}

	/**
	 * Used to check the correctness of the users answer. 
	 * 
	 * @param userAnswer the answer supplied by the user
	 * @return boolean   true if correct, false if incorrect
	 */
	public boolean checkGameAnswer(String userAnswer) {
		QuestionHelper helper = QuestionHelper.getInstance();
		boolean correct = helper.checkQuestion(userAnswer, _currentAnswer);
		deleteQuestionFromFile();
		return correct;
	}

	/**
	 * This method is used to delete questions from game files once they have been answered. 
	 */
	private void deleteQuestionFromFile() {
		//Remove question
		try {
			File tempfile = new File(GameFiles.getUserCategories() + FileHelper.FILE_SEPARATOR + "temp.txt");

			BufferedReader in = new BufferedReader(new FileReader(_questionFile));
			PrintWriter out = new PrintWriter(new FileWriter(tempfile));

			int count = 1;
			String line;
			while((line = in.readLine()) != null) {
				if(!(count == _lineNumber)) {
					out.write(line + FileHelper.LINE_SEPARATOR);
				}
				count++;
			}
			in.close();
			out.close();
			tempfile.renameTo(_questionFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
