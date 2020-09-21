package application.model.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import application.model.helper.FileHelper;

/**
 * 
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
	 * @return
	 */
	public String retrieveQuestion(String category) {
		//Replace spaces from category to hyphen if not already done
		category = category.replace(' ', '-');

		String questionStr = PracticeFiles._categoryFolder + FileHelper.FILESEPARATOR + category + ".txt";
		File questionFile = new File(questionStr);

		generateRandomQuestion(FileHelper.countLinesinFile(questionFile), questionFile);

		return _currentQuestion;
	}

	/**
	 * Generate a random question based on the chosen category of the user.
	 * 
	 * @param numberOfQuestions the number of questions in a given category
	 * @param questionFile      the file which contains the questions of a given category
	 */
	private void generateRandomQuestion(int numberOfQuestions, File questionFile) {
		int randomNumber = 1 + (int)(Math.random() * ((numberOfQuestions - 1) + 1));
		if (questionFile.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(questionFile));
				int count = 1;
				String line;
				while((line = in.readLine()) != null) {
					if (count == randomNumber) {
						String[] separated = line.split(",");
						_currentAnswer = separated[separated.length - 1];

						//Clear question before changing it 
						_currentQuestion = null;
						for(int i = 0; i <= separated.length - 2; i++) {
							if (_currentQuestion != null) {
								_currentQuestion = _currentQuestion + "," + separated[i];
							} else {
								_currentQuestion = separated[i];
							}
						}
					}
					count++;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * used to check the correctness of the users answer. 
	 * 
	 * @param userAnswer       the answer supplied by the user
	 * @param numberOfAttempts the number of attempts the user has had on this particular question
	 * @return String          a string based on how many attempts the user has had and the answer they supply
	 */
	public String checkAnswer(String userAnswer, int numberOfAttempts) {
		if (userAnswer.toLowerCase().contains(_currentAnswer.toLowerCase())) {
			return "Correct!";
		}
		return "Incorrect, the correct answer was: " + _currentAnswer;
	}
}
