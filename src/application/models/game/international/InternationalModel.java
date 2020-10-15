package application.models.game.international;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

import application.controllers.helper.ExceptionAlert;
import application.models.helper.QuestionHelper;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the international section based on what the user wants to
 * do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public final class InternationalModel {

    private static InternationalModel _instance;

    private static final String URL = "https://jservice.io/";
    private static final String RANDOM = "/api/random";

    private HttpClient _client = HttpClient.newHttpClient();

    private String _currentQuestion;
    private String _currentAnswer;
    private int _currentValue;
    private String _currentCategory;
    private int _currentScore = 0;

    private InternationalModel() {
    }

    /**
     * Used to return the single instance of this class.
     * 
     * @return InternationalModel
     */
    public static InternationalModel getInstance() {
        if (_instance == null) {
            _instance = new InternationalModel();
        }
        return _instance;
    }

    /**
     * Used to set up for getting an international question.
     */
    public void retrieveQuestion() {
        JSONArray array = null;
        try {

            HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL + RANDOM)).GET().build();

            HttpResponse<String> response = _client.send(request, BodyHandlers.ofString());

            array = new JSONArray(response.body());
        } catch (Exception e) {
            new ExceptionAlert(e);
        }

        JSONObject jsonObject = array.getJSONObject(0);
        _currentQuestion = jsonObject.getString("question");
        _currentQuestion = capitalise(_currentQuestion);

        _currentAnswer = jsonObject.getString("answer");
        _currentAnswer = capitalise(_currentAnswer);
        _currentAnswer = removeHTML(_currentAnswer);

        // Catch exception if there is an error with jsonObj due to bad api
        try {
            _currentValue = jsonObject.getInt("value");
        } catch (Exception e) {
            _currentValue = Integer.parseInt(jsonObject.getString("value"));
        }

        JSONObject categoryObject = jsonObject.getJSONObject("category");

        _currentCategory = categoryObject.getString("title");
        _currentCategory = capitalise(_currentCategory);
    }

    /**
     * Used to remove HTML itallics from questions if there are any present.
     * Jeopardy api lacks consistency and there are some errors in it.
     * 
     * @param answer
     * @return String
     */
    private String removeHTML(final String answer) {
        return answer.replace("<i>", "").replace("</i>", "");
    }

    /**
     * Capitalise first letter of given string.
     * 
     * @param input
     * @return String
     */
    public String capitalise(final String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);

    }

    /**
     * Get score from international section.
     * 
     * @return score
     */
    public int getInternationalScore() {
        // TODO
        return 0;
    }

    /**
     * Add score from international section.
     * 
     * @return score
     */
    public int addInternationalScore() {
        // TODO - also add button for displaying the score on model.g
        return 0;
    }

    /**
     * Used to get the randomised international question.
     * 
     * @return String the question to be displayed to the user
     */
    public String getInternationalQuestion() {
        return _currentQuestion;
    }

    /**
     * Used to get the randomised international question answer.
     * 
     * @return String the answer to the current question
     */
    public String getInternationalAnswer() {
        return _currentAnswer;
    }

    /**
     * Used to get the value of the current international question.
     * 
     * @return String the answer to the current question
     */
    public int getInternationalValue() {
        return _currentScore;
    }

    /**
     * Used to get the category of the current international question.
     * 
     * @return String the current international category
     */
    public String getInternationalCategory() {
        return _currentCategory;
    }

    /**
     * Used to check whether the users answer for the current question is correct or
     * not.
     * 
     * @param userAnswer
     * @return boolean true if correct, false if incorrect
     */
    public boolean checkInternationalAnswer(final String userAnswer) {
        QuestionHelper helper = QuestionHelper.getInstance();
        return helper.compareAnswers(userAnswer, _currentAnswer);
    }
}
