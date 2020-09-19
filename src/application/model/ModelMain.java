package application.model;

import application.model.game.GameModel;
import application.model.practice.PracticeModel;

public class ModelMain {
	
	private GameModel _gameModel = GameModel.getInstance();
	private PracticeModel _practiceModel = PracticeModel.getInstance();
	
	private static ModelMain _instance;

	private ModelMain() {
	}
	
	public static ModelMain getInstance() {
		if (_instance == null) {
			_instance = new ModelMain();
		}
		return _instance;
	}
	
	public void startGame() {
		_practiceModel.setUpPracticeModule();
		_gameModel.setUpGameModule();
	}
	
	public String getQuestion() {
		return _practiceModel.getQuestion("Geography");
	}
	
	public static void main(String[] args) {
		ModelMain main = ModelMain.getInstance();
		main.startGame();
		main.getQuestion();
	}

}
