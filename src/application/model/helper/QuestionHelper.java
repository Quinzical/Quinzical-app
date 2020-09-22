package application.model.helper;

/**
 * This is class is used to aid in the handling of questions and answers. 
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionHelper {

	public QuestionHelper() {
	}

	/**
	 * Used to check and compare the answers between what the user inputs and the correct answer
	 * 
	 * @param userAnswer 
	 * @param correctAnswer
	 * @return boolean true if the users answer is correct, false if the answer is incorrect
	 */
	public static boolean checkQuestion(String userAnswer, String correctAnswer) {
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

			if (numberOfBrackets == 2) {
				correctAnswer = correctAnswer.substring(i + 2, correctAnswer.length());
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
	private static boolean compareAnswers(String userAnswer, String correctAnswer) {
		// Replace spaces between the answers with a letter to avoid any issues with multi-word answers
		userAnswer = userAnswer.replace(' ', 'a');
		correctAnswer = correctAnswer.replace(' ', 'a');
		
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
	            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
