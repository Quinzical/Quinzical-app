package application.model.practice;

import application.model.helper.FileHelper;

public class PracticeFiles {

	public PracticeFiles() {
	}

	public void setUpPracticeModule() {
		//Create subdirectory for game files if not already created
		String gameData = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data";
		FileHelper.makeDirectory(gameData);

		//Create subdirectory for category files if not already created
		String categories = gameData + FileHelper.FILESEPARATOR + "categories";
		FileHelper.makeDirectory(categories);
	}
}
