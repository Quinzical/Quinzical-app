package application.models.practice;

import java.util.List;

import application.models.helper.Category;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the practice module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public interface PracticeModel {
    /**
     * Used to set up the practice module by calling the correct functions from
     * supplementary classes.
     */
    void setUpPracticeModule();

    /**
     * Used to get a random practice question based on the chosen category.
     * 
     * @param category the category chosen by the user
     * @return String the question
     */
    String getPracticeQuestion(Category category);

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @return boolean true if correct, false if incorrect
     */
    boolean checkPracticeAnswer(String userAnswer);

    /**
     * Used to send the clue for the current question to the user. This is only to
     * be used if they are on their third attempt.
     * 
     * @return String the clue for the user
     */
    String getClue();

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current practice categories
     */
    List<Category> getPracticeCategories();

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    String getPracticeAnswer();

    /**
     * Used to get question prompt
     * 
     * @return prompt
     */
    String getPrompt();
}
