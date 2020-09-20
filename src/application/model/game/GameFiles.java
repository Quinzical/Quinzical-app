package application.model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import application.model.helper.FileHelper;
import application.model.practice.PracticeFiles;

public class GameFiles {

	public static String CURRENTUSER = "default";
	private String _userCategories;

	public GameFiles() {
	}

	public void setUser(String username) {
		CURRENTUSER = "default";
	}

	public void setUpGameModule() {
		//Create subdirectory for users if not already created
		String user = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data" + FileHelper.FILESEPARATOR + "users";
		FileHelper.makeDirectory(user);

		String currentDir = setUpUser(user);

		//Create subdirectory for category files if not already created
		_userCategories = currentDir + FileHelper.FILESEPARATOR + "categories";
		FileHelper.makeDirectory(_userCategories);
	}

	private String setUpUser(String userDir) {
		String user = userDir + FileHelper.FILESEPARATOR + CURRENTUSER;
		FileHelper.makeDirectory(user);
		return user;
	}

	public void randomiseCategories() {
		List<Integer> randomFiles = FileHelper.makeRandomList(5);
		List<Integer> randomQuestions = FileHelper.makeRandomList(5);
		createAndFillRandomFiles(randomFiles, randomQuestions);
	}

	private void createAndFillRandomFiles(List<Integer> randomFiles, List<Integer> randomQuestions) {
		int count = 1;
		File categoriesDir = new File(PracticeFiles._categoryFolder);
		if (categoriesDir.exists()) {
			if (categoriesDir.isDirectory()) {
				for (File file : categoriesDir.listFiles()) {
					if (randomFiles.contains(count)) {
						String fileName = file.getName();
						String copyfileName = _userCategories + FileHelper.FILESEPARATOR + fileName;
						File copyFile = new File(copyfileName);
						if (!copyFile.exists()) {
							//Copy content from one file to another
							try {
								BufferedReader in = new BufferedReader(new FileReader(file));
								PrintWriter out = new PrintWriter(copyFile);
								String line;
								while((line = in.readLine()) != null) {
									out.println(line);
								}
								in.close();
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						count++;
					}
				}
			}
		}
	}
}