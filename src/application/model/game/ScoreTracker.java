package application.model.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import application.model.helper.FileHelper;

public class ScoreTracker {

	private final String _scoreFile = FileHelper.CURRENTDIR + FileHelper.FILESEPARATOR + "data" + FileHelper.FILESEPARATOR + "users" + FileHelper.FILESEPARATOR + GameFiles.CURRENTUSER + FileHelper.FILESEPARATOR + "score";

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

	public void addWinnings(int score) {
		int currentScore = getCurrentScore();
		currentScore += score;
		try {
			File tempfile = new File("temp.txt");
			File scoreFile = new File(_scoreFile);
			PrintWriter out = new PrintWriter(new FileWriter(tempfile));
			out.write(currentScore + System.getProperty("line.separator"));
			out.close();
			tempfile.renameTo(scoreFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getCurrentScore() {
		String score = "0";
		try {
			BufferedReader in = new BufferedReader(new FileReader(_scoreFile));
			score = in.readLine();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int currentScore = Integer.parseInt(score);
		return currentScore;
	}
}