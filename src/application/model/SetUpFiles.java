package application.model;

import java.io.File;

public class SetUpFiles {

	private final String _fileSeparator = System.getProperty("file.separator");
	private final String _currentDir = System.getProperty("user.dir");

	public SetUpFiles() {
	}

	public void setUpGame() {
		//Create subdirectory for game files if not already created
		String gameData = _currentDir + _fileSeparator + "data";
		File dataDir = new File(gameData);
		if (!dataDir.exists()) {
			if (!dataDir.isDirectory()) {
				dataDir.mkdir();
			}
		}

		//Create subdirectory for category files if not already created
		String categories = gameData + _fileSeparator + "categories";
		File categoryDir = new File(categories);
		if (!categoryDir.exists()) {
			if (!categoryDir.isDirectory()) {
				categoryDir.mkdir();
			}
		}
	}
}
