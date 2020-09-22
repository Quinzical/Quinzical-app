package application.model.game;

/**
 * This class is used to delegate tasks to different classes who carry out tasks for the function of the games module based on what the user wants to do.
 *  
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 *
 */
public class GameModel {
	
	private static GameModel _instance;
	
	private GameFiles _gameFiles = new GameFiles();
	private GameQuestionQuery _questionQuery = new GameQuestionQuery();
	
	private ScoreTracker _score = new ScoreTracker();
	
	private GameModel() {
	}
	
	/**
	 * Used to return the single instance of this class.
	 * 
	 * @return GameModel
	 */
	public static GameModel getInstance() {
		if (_instance == null) {
			_instance = new GameModel();
		}
		return _instance;
	}
	
	/**
	 * Used to set up the game module by calling the correct functions from supplementary classes.
	 */
	public void setUpGameModule() {
		//Set up files for game module
		_gameFiles.setUpGameModule();
		_gameFiles.randomiseCategories();
		
		//Set up score tracker
		_score.setUpScore();
	}
	
	/**
	 * Save score to the users file, based on how they did answering the question.
	 */
	public void addScore(int score) {
		_score.addWinnings(score);
	}
	
	/**
	 * Get score from file to display to the user.
	 */
	public int getScore() {
		return _score.getCurrentScore();
	}

	/**
	 * Used to get a game question based on the chosen category and the value of the question. 
	 * 
	 * @param category      the category chosen by the user
	 * @param questionValue the value of the question
	 * @return String       the question to be displayed to the user
	 */
	public String getGameQuestion(String category, String questionValue) {
		return _questionQuery.retrieveQuestion(category, questionValue);
	}

}
