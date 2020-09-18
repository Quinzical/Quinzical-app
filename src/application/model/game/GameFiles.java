package application.model.game;

import java.io.File;

import application.model.helper.FileHelper;

public class GameFiles {

	public static String CURRENTUSER = "default";
	private final String _fileSeparator = System.getProperty("file.separator");
	private final String _currentDir = System.getProperty("user.dir");

	public GameFiles() {
	}

	public void setUpGameModule() {
		//Create subdirectory for users if not already created
		String user = _currentDir + _fileSeparator + "data" + _fileSeparator + "users";
		FileHelper.makeDirectory(user);

		String currentDir = setUpUser(user);

		//Create subdirectory for category files if not already created
		String categories = currentDir + _fileSeparator + "categories";
		FileHelper.makeDirectory(categories);
	}

	private String setUpUser(String userDir) {
		String user = userDir + _fileSeparator + CURRENTUSER;
		FileHelper.makeDirectory(user);
		return user;
	}

	public void setUser(String username) {
		CURRENTUSER = "default";
	}

}
