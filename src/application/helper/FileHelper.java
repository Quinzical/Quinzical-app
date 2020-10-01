package application.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to avoid code reuse by having helper functions for setting up files.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class FileHelper {
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");
	public final static String LINE_SEPARATOR = System.getProperty("line.separator");
	public final static String CURRENT_DIR = System.getProperty("user.dir");

	public static void setUpGame() {
		//Create subdirectory for game files if not already created
		String gameData = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data";
		FileHelper.makeDirectory(gameData);

		//Create subdirectory for category files if not already created
		String categories = gameData + FileHelper.FILE_SEPARATOR + "categories";
		FileHelper.makeDirectory(categories);
	}
	/**
	 * Make a directory.
	 * 
	 * @param directoryName the name of the directory to be made
	 */
	public static void makeDirectory(String directoryName) {
		File dir = new File(directoryName);
		if (!dir.exists()) {
			if (!dir.isDirectory()) {
				dir.mkdir();
			}	
		}
	}

	/**
	 * Make a list of random numbers for getting random categories and questions.
	 * 
	 * @param sizeOfList the size of the list needed to be filled with random numbers 
	 * @param maxNumber	 the maximum number from which the random number can be generated
	 * 
	 * @return List<Integer> a list of random numbers
	 */
	public static List<Integer> makeRandomList(int sizeOfList, int minNumber, int maxNumber) {
		List<Integer> randomList = new ArrayList<Integer>();
		for (int i = 0; i < sizeOfList; i++) {
			int randomValue = minNumber + (int)(Math.random() * ((maxNumber - minNumber) + 1));
			if (randomList.contains(randomValue)) {
				i--;
			} else {
				randomList.add(randomValue);
			}
		}
		return randomList;
	}

	/**
	 * Used to count the number of categories in quinzical file
	 * 
	 * @param directory the directory where files should 
	 * @return
	 */
	public static int countCategories() {
		String quinzical = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical" + ".txt";
		File quinzicalFile = new File(quinzical);
		int categoryCount = 0;

		if (quinzicalFile.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));

				int count = 1;
				String line = null;

				while((line = in.readLine()) != null) {
					//Replace spaces with hypens to name the files
					if (count == 1) {
						count++;
						categoryCount++;
					} else if (line.isEmpty()) {
						count = 1;
					} else { 
						count++;
					}
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return categoryCount;
	}

	/**
	 * Used to count the number of files in a directory
	 * 
	 * @param directory the directory where files should 
	 * @return
	 */
	public static int countFilesInDirectory(String directory) {
		File dir = new File(directory);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				return dir.listFiles().length;
			}
		}
		return 0;
	}

	/**
	 * Used to count the number of lines in a given file
	 * 
	 * @param file the file from which the lines are to be counted
	 * @return int the number of lines in the file
	 */
	public static int countLinesinFile(File file) {
		int linecount = 0;
		if (file.exists()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));

				while(in.readLine() != null) {
					linecount++;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return linecount;
	}

	public static List<String> getLineFromFile(File file, int desiredLine) {
		List<String> questionAndAnswer = new ArrayList<String>();
		String currentQuestion = null;
		String currentAnswer = null;
		if (file.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(file));
				int count = 1;
				String line;
				while((line = in.readLine()) != null) {
					if (count == desiredLine) {
						String[] separated = line.split("\\.");
						currentQuestion = separated[0];
						currentAnswer = separated[1];
					}
					count++;
				}			
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			questionAndAnswer.add(currentQuestion);
			questionAndAnswer.add(currentAnswer);
			return questionAndAnswer;
		}
		return null;
	}
}