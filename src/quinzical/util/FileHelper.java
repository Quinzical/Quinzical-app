package quinzical.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import quinzical.controllers.helper.ExceptionAlert;

/**
 * This class is used to avoid code reuse by having helper functions for setting
 * up files.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class FileHelper {

    private FileHelper() {
    }

    /**
     * File separator based on OS
     */
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * Line separator based on OS
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    /**
     * Current working directory
     */
    public static final String CURRENT_DIR = System.getProperty("user.dir");

    /**
     * SQL data file
     */
    public static final String SQLITE_DB_FILE = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data"
            + FileHelper.FILE_SEPARATOR + "data.db";

    /**
     * Setup game data files
     */
    public static void setUpGame() {
        // Create subdirectory for game files if not already created
        String gameData = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data";
        FileHelper.makeDirectory(gameData);

        // Create subdirectory for category files if not already created
        String categories = gameData + FileHelper.FILE_SEPARATOR + "categories";
        FileHelper.makeDirectory(categories);
    }

    /**
     * Make a directory.
     * 
     * @param directoryName the name of the directory to be made
     */
    public static void makeDirectory(final String directoryName) {
        File dir = new File(directoryName);
        if (!dir.exists()) {
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
        }
    }

    /**
     * Make a list of random numbers for getting random categories and questions.
     * 
     * @param sizeOfList the size of the list needed to be filled with random
     *                   numbers
     * @param minNumber  the minimum number from which the random number can be
     *                   generated
     * @param maxNumber  the maximum number from which the random number can be
     *                   generated
     * 
     * @return List<Integer> a list of random numbers
     */
    public static List<Integer> makeRandomList(final int sizeOfList, final int minNumber, final int maxNumber) {
        List<Integer> randomList = new ArrayList<Integer>();
        for (int i = 0; i < sizeOfList; i++) {
            int randomValue = minNumber + (int) (Math.random() * ((maxNumber - minNumber) + 1));
            if (randomList.contains(randomValue)) {
                i--;
            } else {
                randomList.add(randomValue);
            }
        }
        return randomList;
    }

    /**
     * Used to count the number of categories in quinzical file
     * 
     * @return number of categories
     */
    public static int countCategories() {
        String quinzical = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical" + ".txt";
        File quinzicalFile = new File(quinzical);
        int categoryCount = 0;

        if (quinzicalFile.isFile()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));

                int count = 1;
                String line = null;

                while ((line = in.readLine()) != null) {
                    // Replace spaces with hypens to name the files
                    if (count == 1) {
                        count++;
                        categoryCount++;
                    } else if (line.isEmpty()) {
                        count = 1;
                    } else {
                        count++;
                    }
                }
                in.close();
            } catch (IOException e) {
                new ExceptionAlert(e);
                e.printStackTrace();
            }

        }
        return categoryCount;
    }

    /**
     * Used to count the number of files in a directory
     * 
     * @param directory the directory where files should
     * @return number of files in directory
     */
    public static int countFilesInDirectory(final String directory) {
        File dir = new File(directory);
        if (dir.exists()) {
            if (dir.isDirectory()) {
                return dir.listFiles().length;
            }
        }
        return 0;
    }

    /**
     * Used to count the number of lines in a given file
     * 
     * @param file the file from which the lines are to be counted
     * @return int the number of lines in the file
     */
    public static int countLinesinFile(final File file) {
        int linecount = 0;
        if (file.exists()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));

                while (in.readLine() != null) {
                    linecount++;
                }
                in.close();
            } catch (IOException e) {
                new ExceptionAlert(e);
                e.printStackTrace();
            }
        }
        return linecount;
    }

    /**
     * Used to getLineFromFile
     * 
     * @param file        the file from which to get lines from
     * @param desiredLine the desired line to get
     * 
     * @return List of lines from the file
     * @throws ArrayIndexOutOfBoundsException
     */
    public static List<String> getLineFromFile(final File file, final int desiredLine)
            throws ArrayIndexOutOfBoundsException {
        List<String> questionAndAnswer = new ArrayList<String>();
        String currentQuestion = null;
        String currentAnswer = null;
        if (file.isFile()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(file));
                int count = 1;
                String line;
                while ((line = in.readLine()) != null) {
                    if (count == desiredLine) {
                        String[] separated = line.split("\\\\");
                        currentQuestion = separated[0];
                        currentAnswer = separated[1];
                    }
                    count++;
                }
                in.close();

                questionAndAnswer.add(currentQuestion);
                questionAndAnswer.add(currentAnswer);
                return questionAndAnswer;
            } catch (IOException e) {
                new ExceptionAlert(e);
                e.printStackTrace();
            }
        }
        return questionAndAnswer;
    }

    /**
     * Check if a file exist at path
     * 
     * @param filePathString
     * @return file exist
     */
    public static boolean checkIfFileExist(final String filePathString) {
        File f = new File(filePathString);
        return f.exists() && !f.isDirectory();
    }
}
