package application.models.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import application.models.helper.Category;
import application.helper.FileHelper;
import application.models.practice.PracticeFiles;

/**
 * This class is used to set up the necessary files for the games module. This includes setting up files to account for the need of different users in the project. 
 * later on. 
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameFiles {

	private static String _currentUser = "default";
	private static String _currentUserDir;
	private static String _userCategories;
	private List<Category> _categoryCollection;

	public GameFiles() {
        _categoryCollection = new ArrayList<Category>();
    }
    
    /**
     * Used to get the static user categories directory.
     * 
     * @return userCategories
     */
    public static String getUserCategories() {
        return _userCategories;
    }
    
    /**
     * Used to get the current user of the game.
     * 
     * @return username
     */
    public static String getUser() {
        return _currentUser;
    }

	/**
	 * Used to set the current user of the game for the project later on. 
	 * 
	 * @param username
	 */
	public static void setUser(String username) {
		_currentUser = username;
    }

	/**
	 * Method to create directories for storing game files for the games module.
	 */
	public void setUpGameModule() {
		//Create subdirectory for users if not already created
		String user = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data" + FileHelper.FILE_SEPARATOR + "users";
		FileHelper.makeDirectory(user);

		String currentDir = setUpUser(user);

		//Create subdirectory for category files if not already created
		_userCategories = currentDir + FileHelper.FILE_SEPARATOR + "categories";
		FileHelper.makeDirectory(_userCategories);
	}

	/**
	 * Method to create directory for storing the given users data if not already created. 
	 * 
	 * @param userDir the directory where the users files should be stored
	 * @return String the user directory
	 */
	private String setUpUser(String usersDir) {
		_currentUserDir = usersDir + FileHelper.FILE_SEPARATOR + _currentUser;
		FileHelper.makeDirectory(_currentUserDir);
		return _currentUserDir;
	}

	/**
	 * Used to randomise the categories that the user will be given for questioning.
	 */
	public void randomiseCategories() {
		List<Integer> randomFiles = FileHelper.makeRandomList(5, FileHelper.countFilesInDirectory(PracticeFiles.getCategoryFolder()));
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
		File categoriesDir = new File(PracticeFiles.getCategoryFolder());
		
		if (categoriesDir.exists()) {
			if (categoriesDir.isDirectory()) {
				for (File file : categoriesDir.listFiles()) {
					if (currentNumberOfGameFiles == 5) {
						return;
					} else if (randomFiles.contains(count)) {
						String fileName = file.getName();
						_categoryCollection.add(new Category(fileName.replace(".txt", "")));
						String copyFileName = _userCategories + FileHelper.FILE_SEPARATOR + fileName;

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

	/**
	 * Method to return the practice categories. 
	 * 
	 * @return List<Category> a list containing all the current categories
	 */
	public List<Category> getCategories() {
		if(_categoryCollection.isEmpty()) {
			getCategoriesFromDirectory();
		}
		return _categoryCollection;
	}
	
	/**
	 * Get the categories from a game save. 
	 */
	private void getCategoriesFromDirectory() {
		File categoriesDir = new File(_userCategories);
		for (File file : categoriesDir.listFiles()) {
				String fileName = file.getName();
				_categoryCollection.add(new Category(fileName));
		}
	}

	/**
	 * Method to reset the game for the current user. 
	 */
	public void resetGame() {
		ProcessBuilder pb = new ProcessBuilder().command("rm", "-r", _currentUserDir);
		try {
			Process process = pb.start();	
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		setUpGameModule();
		randomiseCategories();
	}
}