package application.model.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import application.model.practice.PracticeFiles;

/**
 * This class is used to avoid code reuse by having helper functions for setting up files.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class FileHelper {
	public final static String FILESEPARATOR = System.getProperty("file.separator");
	public final static String CURRENTDIR = System.getProperty("user.dir");

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
	public static List<Integer> makeRandomList(int sizeOfList, int maxNumber) {
		List<Integer> randomList = new ArrayList<Integer>();
		for (int i = 0; i < sizeOfList; i++) {
			int randomValue = 1 + (int)(Math.random() * ((maxNumber - 1) + 1));
			if (randomList.contains(randomValue)) {
				i--;
			} else {
				randomList.add(randomValue);
			}
		}
		return randomList;
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
				String line;
				
				while((line = in.readLine()) != null) {
					linecount++;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return linecount;
	}
}
