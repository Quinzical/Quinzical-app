package application.model;

import application.model.game.GameModel;
import application.model.practice.PracticeModel;
import application.model.question.QuestionModel;

/**
 * This is the main class for the model, where the game, practice and question models can be accessed. 
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ModelMain {
	
	private GameModel _gameModel = GameModel.getInstance();
	private PracticeModel _practiceModel = PracticeModel.getInstance();
	
	private static ModelMain _instance;

	private ModelMain() {
	}
	
	/**
	 * Gets instance of this main model class.
	 * 
	 * @return ModelMain
	 */
	public static ModelMain getInstance() {
		if (_instance == null) {
			_instance = new ModelMain();
		}
		return _instance;
	}
	
	/**
	 * Used to set up the files needed for the game.
	 */
	public void startGame() {
		//Practice model must be set up before game model
		_practiceModel.setUpPracticeModule();
		_gameModel.setUpGameModule();
	}
	
	/**
	 * Used to get a question for either the game or practice module, depending on the inputs.
	 * 
	 * @return String the question to be displayed to the user
	 */
	public String getQuestion() {
		QuestionModel questionModel = QuestionModel.getInstance();
		questionModel.setCategory("Geography");
		questionModel.setPractice(false);
		questionModel.setQuestionValue("500");
		System.out.println(questionModel.getQuestion());
		
		return null;
	}
	
	public void checkUserAnswer(String userAnswer) {
		QuestionModel questionModel = QuestionModel.getInstance();
		questionModel.checkAnswer(userAnswer);
	}
	
	/**
	 * Used for testing.
	 */
	public static void main(String[] args) {
		ModelMain main = ModelMain.getInstance();
		main.startGame();
		main.getQuestion();
		main.checkUserAnswer("Stewart");
	}

}
