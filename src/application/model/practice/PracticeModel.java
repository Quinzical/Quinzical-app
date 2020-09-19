package application.model.practice;

public class PracticeModel {
	
	private static PracticeModel _instance;
	
	private PracticeFiles _practiceFiles = new PracticeFiles();
	private PracticeQuestionQuery _questionQuery = new PracticeQuestionQuery();
	
	private PracticeModel() {
	}
	
	public static PracticeModel getInstance() {
		if (_instance == null) {
			_instance = new PracticeModel();
		}
		return _instance;
	}
	
	public void setUpPracticeModule() {
		_practiceFiles.setUpPracticeModule();
		_practiceFiles.copyCategories();
	}
	
	public String getPracticeQuestion(String category) {
		return _questionQuery.retrieveQuestion(category);
	}

	public String checkPracticeAnswer(String userAnswer, int numberOfAttempts) {
		return _questionQuery.checkAnswer(userAnswer, numberOfAttempts);
	}
}
