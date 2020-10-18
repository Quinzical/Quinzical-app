package application.models.practice.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.models.helper.Category;
import application.models.helper.QuestionHelper;
import application.models.practice.PracticeModel;
import application.models.sql.data.CategoryData;
import application.models.sql.data.QuestionData;
import application.models.sql.db.CategoryDB;
import application.models.sql.db.QuestionDB;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the practice module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class PracticeModelSQL implements PracticeModel {

    private static PracticeModelSQL _instance;

    private int _questionID;

    private QuestionDB _questionDB = new QuestionDB();
    private CategoryDB _categoryDB = new CategoryDB();

    private PracticeModelSQL() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return PracticeModel
     */
    public static PracticeModelSQL getInstance() {
        if (_instance == null) {
            _instance = new PracticeModelSQL();
        }
        return _instance;
    }

    /**
     * Used to set up the practice module by calling the correct functions from
     * supplementary classes.
     */
    public void setUpPracticeModule() {
    }

    /**
     * Used to get a random practice question based on the chosen category.
     * 
     * @param category the category chosen by the user
     * @return String the question
     */
    public String getPracticeQuestion(final Category category) {
        try {
            QuestionData question = _questionDB.randomQuestion(category.getCategoryID());
            _questionID = question.getID();
            return question.getQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @return boolean true if correct, false if incorrect
     */
    public boolean checkPracticeAnswer(final String userAnswer) {
        QuestionHelper helper = QuestionHelper.getInstance();
        try {
            List<String> answers = _questionDB.getAnswers(_questionID);
            return helper.compareAnswers(answers, helper.removePrompt(userAnswer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Used to send the clue for the current question to the user. This is only to
     * be used if they are on their third attempt.
     * 
     * @return String the clue for the user
     */
    public String getClue() {
        try {
            List<String> question = _questionDB.getAnswers(_questionID);
            String answer = question.get(0);
            for (int i = 0; i < answer.length(); i++) {
                if (Character.isUpperCase(answer.charAt(i))) {
                    return Character.toString(answer.charAt(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Used to deliver a list of categories to the required controllers.
     * 
     * @return List<Category> the current practice categories
     */
    public List<Category> getPracticeCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            List<CategoryData> data = _categoryDB.query();
            for (CategoryData category : data) {
                categories.add(new Category(category.getName(), category.getID()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Used to get the correct answer for the current question.
     * 
     * @return String the current answer
     */
    public String getPracticeAnswer() {
        try {
            List<String> question = _questionDB.getAnswers(_questionID);
            return question.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Used to get practice model question prompt
     * 
     * @return prompt
     */
    public String getPrompt() {
        try {
            QuestionData question = _questionDB.query(_questionID);
            return question.getQualifier();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
