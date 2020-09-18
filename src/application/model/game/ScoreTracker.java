package application.model.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreTracker {

	private final String _fileSeparator = System.getProperty("file.separator");
	private final String _currentDir = System.getProperty("user.dir");
	private final String _scoreFile = _currentDir + _fileSeparator + "data" + _fileSeparator + "users" + _fileSeparator + GameFiles.CURRENTUSER + _fileSeparator + "score";

	public ScoreTracker() {
	}

	public void setUpScore() {
		//Create file to keep track of players winnings if not already created
		try {
			File sFile = new File(_scoreFile);
			if (!sFile.exists()) {
				FileWriter out = new FileWriter(sFile);
				out.append("0");
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
