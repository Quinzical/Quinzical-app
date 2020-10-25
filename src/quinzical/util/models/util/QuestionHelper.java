package quinzical.util.models.util;

import java.io.IOException;
import java.util.List;

import quinzical.controllers.util.alerts.ExceptionAlert;

/**
 * This is class is used to aid in the handling of questions and answers.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class QuestionHelper {

    private static QuestionHelper _instance;

    private static final String[] PROMPTS = new String[]{
        "what is",
        "what are",
        "who is",
        "who are",
    };

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
     * Used to check and compare the answers between what the user inputs and the
     * correct answer
     * 
     * @param userAnswer
     * @param correctAnswer
     * @return boolean true if the users answer is correct, false if the answer is
     *         incorrect
     */
    public boolean checkQuestion(final String userAnswer, final String correctAnswer) {
        String trimmedAnswer = trimAnswer(correctAnswer);
        if (compareAnswer(userAnswer, trimmedAnswer)) {
            return true;
        }
        return false;
    }

    /**
     * Remove "(What is)" etc from answer to make it easier to check the answer.
     * 
     * @param correctAnswer
     * @return tirmmed answer
     */
    public static String trimAnswer(final String correctAnswer) {
        int numberOfBrackets = 0;
        for (int i = 0; i <= correctAnswer.length(); i++) {
            if (correctAnswer.charAt(i) == '(' || correctAnswer.charAt(i) == ')') {
                numberOfBrackets++;
            }

            if ((numberOfBrackets == 2 && Character.isUpperCase(correctAnswer.charAt(i)))
                    || (numberOfBrackets == 2 && Character.isDigit(correctAnswer.charAt(i)))) {
                return correctAnswer.substring(i, correctAnswer.length());
            }
        }
        return correctAnswer;
    }

    /**
     * Compares the users answer to the correct answer and returns a boolean based
     * on the correctness of the user answer.
     * 
     * @param userAnswer    the answer supplied by the user
     * @param correctAnswer the correct answer
     * @return boolean true if correct, false if incorrect
     */
    public boolean compareAnswer(final String userAnswer, final String correctAnswer) {
        if (userAnswer.toLowerCase().contains((correctAnswer.toLowerCase()))) {
            int startingIndex = userAnswer.toLowerCase().indexOf(correctAnswer.toLowerCase());
            int endIndex = startingIndex + correctAnswer.length() - 1;
            if ((startingIndex != 0 && userAnswer.charAt(startingIndex - 1) != ' ')
                    || (endIndex != userAnswer.length() - 1 && userAnswer.charAt(endIndex + 1) != ' ')) {
                return false;
            }
            return true;
        } else if (correctAnswer.contains("/")) {
            try {
                String command = "echo " + "\"" + correctAnswer + "\"" + " | grep -i -w " + "\"" + userAnswer + "\"";

                ProcessBuilder pb = new ProcessBuilder().command("bash", "-c", command);
                Process process = pb.start();

                int exitStatus = process.waitFor();

                return exitStatus == 0;
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

    /**
     * Compare userAnswers to answer in a list
     * 
     * @param answers
     * @param userAnswer
     * @return boolean true if correct, false if incorrect
     */
    public boolean compareAnswers(final List<String> answers, final String userAnswer) {
        String user = userAnswer.toLowerCase().replace(" ", "").replace("the", "");
        for (String answer : answers) {
            answer = answer.toLowerCase().replace(" ", "").replace("the", "");
            if (answer.equals(user)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used to retrieve question prompt
     * 
     * @param correctAnswer
     * @return prompt
     */
    public String retrievePrompt(final String correctAnswer) {
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

    /**
     * Used to remove prompt from user answers
     * 
     * @param answer
     * @return trimmed answer
     */
    public String removePrompt(final String answer) {
        String trimmed = answer.toLowerCase().trim();
        for (String prompt: PROMPTS) {
            trimmed = trimmed.replace(prompt, "").trim();
        }
        return trimmed;
    }
}
