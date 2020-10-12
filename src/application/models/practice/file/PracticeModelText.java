package application.models.practice.file;

import java.util.List;

import application.helper.FileHelper;
import application.models.helper.Category;
import application.models.practice.PracticeModel;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the practice module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class PracticeModelText implements PracticeModel {

    private static PracticeModelText _instance;

    private PracticeFiles _practiceFiles = new PracticeFiles();
    private PracticeQuestionQuery _questionQuery = new PracticeQuestionQuery();

    private PracticeModelText() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return PracticeModel
     */
    public static PracticeModelText getInstance() {
        if (_instance == null) {
            _instance = new PracticeModelText();
        }
        return _instance;
    }

    /**
     * Used to set up the practice module by calling the correct functions from
     * supplementary classes.
     */
    public void setUpPracticeModule() {
        FileHelper.setUpGame();
        _practiceFiles.copyCategories();
    }

    /**
     * Used to get a random practice question based on the chosen category.
     * 
     * @param category the category chosen by the user
     * @return String the question
     */
    public String getPracticeQuestion(final Category category) {
        return _questionQuery.retrieveQuestion(category);
    }

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @return boolean true if correct, false if incorrect
     */
    public boolean checkPracticeAnswer(final String userAnswer) {
        return _questionQuery.checkPracticeAnswer(userAnswer);
    }

    /**
     * Used to send the clue for the current question to the user. This is only to
     * be used if they are on their third attempt.
     * 
     * @return String the clue for the user
     */
    public String getClue() {
        return _questionQuery.getClueFromQuestion();
    }

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current practice categories
     */
    public List<Category> getPracticeCategories() {
        return _practiceFiles.getCategories();
    }

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    public String getPracticeAnswer() {
        return _questionQuery.retrieveAnswer();
    }

    /**
     * Used to get practice model question prompt
     * 
     * @return prompt
     */
    public String getPrompt() {
        return _questionQuery.getPrompt();
    }
}
