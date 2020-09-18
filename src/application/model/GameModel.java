package application.model;

import application.model.game.GameFiles;
import application.model.game.ScoreTracker;

public class GameModel {
	
	public static void main(String[] args) {
		//Set up files for practice module
		SetUpFiles setUp = new SetUpFiles();
		setUp.setUpGame();
		
		//Set up files for game module
		GameFiles game = new GameFiles();
		game.setUpGameModule();
		
		//Set up score tracker
		ScoreTracker score = new ScoreTracker();
		score.setUpScore();
	}

}
