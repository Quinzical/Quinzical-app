package application.model.practice;

import application.model.helper.FileHelper;

public class PracticeFiles {

	public PracticeFiles() {
	}

	public void setUpPracticeModule() {
		//Create subdirectory for game files if not already created
		String gameData = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data";
		FileHelper.makeDirectory(gameData);

		//Create subdirectory for category files if not already created
		String categories = gameData + FileHelper.FILESEPARATOR + "categories";
		FileHelper.makeDirectory(categories);
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
