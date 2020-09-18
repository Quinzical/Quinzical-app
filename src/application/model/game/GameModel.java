package application.model.game;

public class GameModel {
	
	private static GameModel _instance;
	
	private GameFiles _gameFiles = new GameFiles();
	private ScoreTracker _score = new ScoreTracker();
	
	private GameModel() {
	}
	
	public static GameModel getInstance() {
		if (_instance == null) {
			_instance = new GameModel();
		}
		return _instance;
	}
	
	public void setUpGameModule() {
		//Set up files for game module
		_gameFiles = new GameFiles();
		_gameFiles.setUpGameModule();
		
		//Set up score tracker
		_score.setUpScore();
	}
	
	public void addScore(int score) {
		_score.addWinnings(score);
	}
	
	public int getScore() {
		return _score.getCurrentScore();
	}

}
