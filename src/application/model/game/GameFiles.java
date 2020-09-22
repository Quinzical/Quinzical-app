package application.model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import application.model.helper.FileHelper;
import application.model.practice.PracticeFiles;

/**
 * This class is used to set up the necessary files for the games module. This includes setting up files to account for the need of different users in the project. 
 * later on. 
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameFiles {

	public static String CURRENTUSER = "default";
	public static String _userCategories;

	
	public GameFiles() {
	}

	/**
	 * Used to set the current user of the game for the project later on. 
	 * 
	 * @param username
	 */
	public void setUser(String username) {
		CURRENTUSER = "default";
	}

	/**
	 * Method to create directories for storing game files for the games module.
	 */
	public void setUpGameModule() {
		//Create subdirectory for users if not already created
		String user = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data" + FileHelper.FILESEPARATOR + "users";
		FileHelper.makeDirectory(user);

		String currentDir = setUpUser(user);

		//Create subdirectory for category files if not already created
		_userCategories = currentDir + FileHelper.FILESEPARATOR + "categories";
		FileHelper.makeDirectory(_userCategories);
	}

	/**
	 * Method to create directory for storing the given users data if not already created. 
	 * 
	 * @param userDir the directory where the users files should be stored
	 * @return String the user directory
	 */
	private String setUpUser(String userDir) {
		String user = userDir + FileHelper.FILESEPARATOR + CURRENTUSER;
		FileHelper.makeDirectory(user);
		return user;
	}

	/**
	 * Used to randomise the categories that the user will be given for questioning.
	 */
	public void randomiseCategories() {
		List<Integer> randomFiles = FileHelper.makeRandomList(5, FileHelper.countFilesInDirectory(PracticeFiles._categoryFolder));
		createAndFillRandomFiles(randomFiles);
	}

	/**
	 * Used to fill the random categories with five random questions.
	 * 
	 * @param randomFiles a list of random numbers for choosing files
	 */
	private void createAndFillRandomFiles(List<Integer> randomFiles) {
		int currentNumberOfGameFiles = FileHelper.countFilesInDirectory(_userCategories);
		int count = 1;
		File categoriesDir = new File(PracticeFiles._categoryFolder);
		
		if (categoriesDir.exists()) {
			if (categoriesDir.isDirectory()) {
				for (File file : categoriesDir.listFiles()) {
					if (currentNumberOfGameFiles == 5) {
						return;
					} else if (randomFiles.contains(count)) {
						String fileName = file.getName();
						String copyFileName = _userCategories + FileHelper.FILESEPARATOR + fileName;
						File copyFile = new File(copyFileName);
						
						List<Integer> randomQuestions = FileHelper.makeRandomList(5, FileHelper.countLinesinFile(file));
						int linecount = 1;
						
						if (!copyFile.exists()) {
							//Copy content from one file to another
							try {
								BufferedReader in = new BufferedReader(new FileReader(file));
								PrintWriter out = new PrintWriter(copyFile);
								String line;
								
								while((line = in.readLine()) != null) {
									if (randomQuestions.contains(linecount)) {
										out.println(line);
									}
									linecount++;
								}
								in.close();
								out.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					count++;
				}
			}
		}
	}
}