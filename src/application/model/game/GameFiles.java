package application.model.game;

import java.io.File;

public class GameFiles {

	public static String CURRENTUSER = "default";
	private final String _fileSeparator = System.getProperty("file.separator");
	private final String _currentDir = System.getProperty("user.dir");

	public GameFiles() {
	}

	public void setUpGameModule() {
		//Create subdirectory for users if not already created
		String user = _currentDir + _fileSeparator + "data" + _fileSeparator + "users";
		File userDir = new File(user);
		if (!userDir.exists()) {
			if (!userDir.isDirectory()) {
				userDir.mkdir();
			}
		}

		String currentDir = setUpUser(user);

		//Create subdirectory for category files if not already created
		String categories = currentDir + _fileSeparator + "categories";
		File categoryDir = new File(categories);
		if (!categoryDir.exists()) {
			if (!categoryDir.isDirectory()) {
				categoryDir.mkdir();
			}
		}


	}

	private String setUpUser(String userDir) {
		String user = userDir + _fileSeparator + CURRENTUSER;
		File dirForUser = new File(user);
		if (!dirForUser.exists()) {
			if (!dirForUser.isDirectory()) {
				dirForUser.mkdir();
			}
		}
		return user;
	}

	public void setUser(String username) {
		CURRENTUSER = "default";
	}

}
