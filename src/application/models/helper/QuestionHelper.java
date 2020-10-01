package application.models.helper;

import java.io.IOException;

import application.controllers.helper.ExceptionAlert;

/**
 * This is class is used to aid in the handling of questions and answers.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionHelper {

	private static QuestionHelper _instance;

	private QuestionHelper() {
	}

	/**
	 * Used to return the single instance of this class.
	 * 
	 * @return PracticeModel
	 */
	public static QuestionHelper getInstance() {
		if (_instance == null) {
			_instance = new QuestionHelper();
		}
		return _instance;
	}

	/**
	 * Used to check and compare the answers between what the user inputs and the correct answer
	 * 
	 * @param userAnswer 
	 * @param correctAnswer
	 * @return boolean true if the users answer is correct, false if the answer is incorrect
	 */
	public boolean checkQuestion(String userAnswer, String correctAnswer) {
		correctAnswer = trimAnswer(correctAnswer);
		if(compareAnswers(userAnswer, correctAnswer)) {
			return true;
		}
		return false;
	}

	/**
	 * Remove "(What is)" etc from answer to make it easier to check the answer.
	 * 
	 * @param correctAnswer
	 * @return
	 */
	public static String trimAnswer(String correctAnswer) {
		int numberOfBrackets = 0;
		for (int i = 0; i <= correctAnswer.length(); i++) {
			if (correctAnswer.charAt(i) == '(' || correctAnswer.charAt(i) == ')') {
				numberOfBrackets++;
			}

			if ((numberOfBrackets == 2 && Character.isUpperCase(correctAnswer.charAt(i))) || (numberOfBrackets == 2 && Character.isDigit(correctAnswer.charAt(i)))) {
				correctAnswer = correctAnswer.substring(i, correctAnswer.length());
				return correctAnswer;
			}
		}
		return correctAnswer;
	}

	/**
	 * Compares the users answer to the correct answer and returns a boolean based on the correctness of the user answer. 
	 * 
	 * @param userAnswer    the answer supplied by the user
	 * @param correctAnswer the correct answer
	 * @return boolean      true if correct, false if incorrect
	 */
	private boolean compareAnswers(String userAnswer, String correctAnswer) {
		if (userAnswer.toLowerCase().contains((correctAnswer.toLowerCase()))) {
			int startingIndex = userAnswer.toLowerCase().indexOf(correctAnswer.toLowerCase());
			int endIndex = startingIndex + correctAnswer.length() - 1;
			if ((startingIndex != 0 && userAnswer.charAt(startingIndex - 1) != ' ') || (endIndex != userAnswer.length() - 1 && userAnswer.charAt(endIndex + 1) != ' ')) {
				return false;
			}
			return true;
		} else if (correctAnswer.contains("/")) {
			try {
				String command = "echo " + "\"" + correctAnswer + "\"" + " | grep -i -w " + "\"" + userAnswer + "\"";

				ProcessBuilder pb = new ProcessBuilder().command("bash", "-c", command);
				Process process = pb.start();

				int exitStatus = process.waitFor();

				if (exitStatus == 0) {
					// The user got it correct
					return true;
				} else {
					// The user did not get it right
					return false;
				}

			} catch (IOException e) {
                new ExceptionAlert(e);
				e.printStackTrace();
			} catch (InterruptedException e) {
                new ExceptionAlert(e);
				e.printStackTrace();
			}
		} 
		return false;
	}

	public String retrievePrompt(String correctAnswer) {
		int numberOfBrackets = 0;
		int firstBracket = 0;
		String prompt = null;
		for (int i = 0; i <= correctAnswer.length(); i++) {
			if (correctAnswer.charAt(i) == '(' || correctAnswer.charAt(i) == ')') {
				if (numberOfBrackets == 0) {
					firstBracket = i;
				}
				numberOfBrackets++;
			}

			if (numberOfBrackets == 2) {
				prompt = correctAnswer.substring(firstBracket + 1, i);
				prompt.trim();
				return prompt;
			}
		}
		return prompt;
	}
}
