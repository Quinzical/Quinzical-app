package application.model.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PracticeFiles {

	private final String _fileSeparator = System.getProperty("file.separator");
	private final String _currentDir = System.getProperty("user.dir");

	private String _categoriesFolder = _currentDir + _fileSeparator + "data" +_fileSeparator + "categories";

	public PracticeFiles() {
	}

	public void setUpPracticeModule() {
		//Create subdirectory for game files if not already created
		String gameData = _currentDir + _fileSeparator + "data";
		File dataDir = new File(gameData);
		if (!dataDir.exists()) {
			if (!dataDir.isDirectory()) {
				dataDir.mkdir();
			}
		}

		//Create subdirectory for category files if not already created
		File categoryDir = new File(_categoriesFolder);
		if (!categoryDir.exists()) {
			if (!categoryDir.isDirectory()) {
				categoryDir.mkdir();
			}
		}
	}

	public void copyCategories() {
		String quinzical = _currentDir + _fileSeparator + "quinzical";
		File quinzicalFile = new File(quinzical);
		if (quinzicalFile.isFile()) {
				try {
					BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));
					PrintWriter out = null;
					
					String line = null;
					int count = 1;
					boolean read = false;
					
					String copyName = null;
					File copyFile = null;
					
					while((line = in.readLine()) != null) {
						if (line.isEmpty()) {
							count = 1;
							read = false;
							out.close();
						} else if (count == 1) {
							copyName = _categoriesFolder + _fileSeparator + line;
							copyFile = new File(copyName);
							out = new PrintWriter(copyFile);
						}
						
						if (read) {
							count++;
							if (!copyFile.exists()) {
								out.println(line);
								System.out.println(line);
							}
						}
					}
					in.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


	}
