package application.model;

import application.model.game.GameFiles;
import application.model.game.ScoreTracker;

public class GameModel {
	
	private static GameModel _instance;
	
	private SetUpFiles _practiceModule = new SetUpFiles();
	private GameFiles _gameModule = new GameFiles();
	private ScoreTracker _score = new ScoreTracker();
	
	private GameModel() {
	}
	
	public GameModel getInstance() {
		if (_instance == null) {
			_instance = new GameModel();
		}
		return _instance;
	}
	
	public void startGame() {
		//Set up files for practice module
		_practiceModule.setUpGame();
		
		//Set up files for game module
		_gameModule = new GameFiles();
		_gameModule.setUpGameModule();
		
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
