package application.model.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import application.model.helper.FileHelper;

/**
 * This class is used to set up the necessary files for the games module.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class PracticeFiles {

	public static String _categoryFolder;

	public PracticeFiles() {
	}

	/**
	 * Method to create directories for storing game files for the practice module.
	 */
	public void setUpPracticeModule() {
		//Create subdirectory for game files if not already created
		String gameData = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data";
		FileHelper.makeDirectory(gameData);

		//Create subdirectory for category files if not already created
		_categoryFolder = gameData + FileHelper.FILESEPARATOR + "categories";
		FileHelper.makeDirectory(_categoryFolder);
	}

	/**
	 * Method to copy categories and their relevant questions and answers into separate files from the quinzical file.
	 */
	public void copyCategories() {
		String quinzical = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "quinzical" + ".txt";
		File quinzicalFile = new File(quinzical);
		if (quinzicalFile.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));
				PrintWriter out = null;

				String line = null;
				int count = 1;
				File copyFile = null;

				while((line = in.readLine()) != null) {
					//Replace spaces with hypens to name the files
					if (count == 1) {
						line = line.replace(' ', '-');
						String copyName = _categoryFolder + FileHelper.FILESEPARATOR + line + ".txt";
						copyFile = new File(copyName);
						out = new PrintWriter(copyFile);
						count++;
					} else if (line.isEmpty()) {
						count = 1;
						out.close();
					} else { 
						count++;
						out.println(line);
					}
				}
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}