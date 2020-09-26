package application.models;

import application.models.game.GameModel;
import application.models.helper.Category;
import application.models.practice.PracticeModel;
import application.models.question.QuestionModel;

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
		questionModel.setCategory(new Category("Fauna"));
		questionModel.setPractice(false);
		questionModel.setQuestionValue("400");
		System.out.println(questionModel.getQuestion());
		questionModel.getClue();
		
		return null;
	}
	
	/**
	 * Used to check whether the user has correctly answered the question or not 
	 * 
	 * @param userAnswer
	 */
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
		main.checkUserAnswer("Rakiura");
	}

	/**
	 * Used to reset the games module if the user presses the 'reset button'. 
	 */
	public void resetGame() {
		_gameModel.resetGameModule();
	}

}
