package application.model.helper;

import java.io.File;

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
}
