package application.model.helper;

/**
 * This is class is used to check the validity of question answers 
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class QuestionChecker {

	private String messageToUser;

	public QuestionChecker() {
	}

	public static boolean checkQuestion(String userAnswer, String correctAnswer) {
		correctAnswer = trimAnswer(correctAnswer);
		if(compareAnswers(userAnswer, correctAnswer)) {
		}
		return false;
	}

	/**
	 * Remove "(What is)" etc from answer to make it easier to check the answer
	 * 
	 * @param correctAnswer
	 * @return
	 */
	private static String trimAnswer(String correctAnswer) {
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

	private static boolean compareAnswers(String userAnswer, String correctAnswer) {
		boolean success = false;
		String[] input = userAnswer.split(" ");
		for(String word : input) {
			if (correctAnswer.toLowerCase().contains(word.toLowerCase())) {
				success = true;
			}
		}
		return success;
	}
}
