package application.model.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import application.model.helper.FileHelper;

public class PracticeQuestionQuery {

	String _currentQuestion = null;
	String _currentAnswer = null;

	public PracticeQuestionQuery() {
	}

	public String retrieveQuestion(String category) {
		//Replace spaces from category to hyphen if not already done
		category = category.replace(' ', '-');

		String questionStr = PracticeFiles._categoryFolder + FileHelper.FILESEPARATOR + category + ".txt";
		File questionFile = new File(questionStr);

		generateRandomQuestion(countNumberOfQuestions(questionFile), questionFile);

		return _currentQuestion;
	}

	private int countNumberOfQuestions(File questionFile) {
		int count = 0;
		if (questionFile.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(questionFile));
				String line;
				while((line = in.readLine()) != null) {
					count++;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	private void generateRandomQuestion(int numberOfQuestions, File questionFile) {
		int randomNumber = 1 + (int)(Math.random() * ((numberOfQuestions - 1) + 1));
		if (questionFile.isFile()) {
			try {
				BufferedReader in = new BufferedReader(new FileReader(questionFile));
				int count = 1;
				String line;
				while((line = in.readLine()) != null) {
					if (count == randomNumber) {
						String[] separated = line.split(",");
						_currentAnswer = separated[separated.length - 1];

						//Clear question before changing it 
						_currentQuestion = null;
						for(int i = 0; i <= separated.length - 2; i++) {
							if (_currentQuestion != null) {
								_currentQuestion = _currentQuestion + "," + separated[i];
							} else {
								_currentQuestion = separated[i];
							}
						}
					}
					count++;
				}
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns "Correct!" if answer is right, "Incorrect" followed by the correct answer if answer is wrong. 
	 */
	public String checkAnswer(String userAnswer, int numberOfAttempts) {
		if (userAnswer.toLowerCase().contains(_currentAnswer.toLowerCase())) {
			return "Correct!";
		}
		return "Incorrect, the correct answer was: " + _currentAnswer;
	}
}
