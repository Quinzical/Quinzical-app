package application.model.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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

	public static List<Integer> makeRandomList(int sizeOfArray, int maxNumber) {
		List<Integer> randomList = new ArrayList<Integer>();
		for (int i = 0; i < sizeOfArray; i++) {
			int randomValue = 1 + (int)(Math.random() * ((maxNumber - 1) + 1));
			if (randomList.contains(randomValue)) {
				i--;
			} else {
				randomList.add(randomValue);
			}
		}
		return randomList;
	}

	public static int countFilesInDirectory(String directory) {
		int count = 0;
		File dir = new File(directory);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					count++;
				}
			}
		}
		return count;
	}

	public static int countLinesinFile(File file) {
		int linecount = 0;
		if (file.exists()) {
			//Copy content from one file to another
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
