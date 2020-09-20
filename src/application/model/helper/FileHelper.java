package application.model.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import application.model.practice.PracticeFiles;

public class FileHelper {
	public final static String FILESEPARATOR = System.getProperty("file.separator");
	public final static String CURRENTDIR = System.getProperty("user.dir");

	public static void makeDirectory(String directoryName) {
		File dir = new File(directoryName);
		if (!dir.exists()) {
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
		}
	}

	public static List<Integer> makeRandomList(int sizeOfArray) {
		int numberOfFiles = countFilesInPracticeModel();
		List<Integer> randomList = new ArrayList<Integer>();
		for (int i = 0; i < sizeOfArray; i++) {
			randomList.add((1 + (int)(Math.random() * ((numberOfFiles - 1) + 1))));
		}
		return randomList;
	}

	private static int countFilesInPracticeModel() {
		int count = 0;
		File categoriesDir = new File(PracticeFiles._categoryFolder);
		if (categoriesDir.exists()) {
			if (categoriesDir.isDirectory()) {
				for (File file : categoriesDir.listFiles()) {
					count++;
				}
			}
		}
		return count;
	}
}
