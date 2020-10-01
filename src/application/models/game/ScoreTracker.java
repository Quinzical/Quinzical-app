package application.models.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import application.helper.FileHelper;

/**
 * This class is used to keep a track of the current users score from the games module.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class ScoreTracker {

	private final String _scoreFile = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data" + FileHelper.FILE_SEPARATOR + "users" + FileHelper.FILE_SEPARATOR + GameFiles.getUser() + FileHelper.FILE_SEPARATOR + "score.txt";

	public ScoreTracker() {
	}

	/** 
	 * Set up file to store a users winnings if not already created.
	 */
	public void setUpScore() {
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

	/**
	 * Save winnings from a question to the file.
	 * 
	 * @param score the amount the user receives for successfully answering the question
	 */
	public void addWinnings(String questionValue) {
		int score = Integer.parseInt(questionValue);
		int currentScore = getCurrentScore();
		currentScore += score;
		try {
			File tempfile = new File("temp.txt");
			File scoreFile = new File(_scoreFile);
			PrintWriter out = new PrintWriter(new FileWriter(tempfile));
			out.write(currentScore + FileHelper.LINE_SEPARATOR);
			out.close();
			tempfile.renameTo(scoreFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the current users score from file.
	 * 
	 * @return int the score of the current user
	 */
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
