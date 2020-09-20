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
}
