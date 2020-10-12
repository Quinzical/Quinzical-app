package application.models.game.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import application.models.helper.Category;
import application.controllers.helper.ExceptionAlert;
import application.helper.FileHelper;

/**
 * This class is used to set up the necessary files for the games module. This
 * includes setting up files to account for the need of different users in the
 * project. later on.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameFiles {

    private static String _currentUser = "default";
    private static String _currentUserDir;

    private static final int NUMBER_OF_QUESTIONS = 5;

    private List<Category> _categoryCollection;

    private static final String USER_CATEGORIES = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data"
            + FileHelper.FILE_SEPARATOR + "users" + FileHelper.FILE_SEPARATOR + _currentUser + FileHelper.FILE_SEPARATOR
            + "categories";

    /**
     * Create a GameFiles instance
     */
    public GameFiles() {
        _categoryCollection = new ArrayList<Category>();
    }

    /**
     * Used to get the static user categories directory.
     * 
     * @return userCategories
     */
    public static String getUserCategories() {
        return USER_CATEGORIES;
    }

    /**
     * Used to get the current user of the game.
     * 
     * @return username
     */
    public static String getUser() {
        return _currentUser;
    }

    /**
     * Used to set the current user of the game for the project later on.
     * 
     * @param username
     */
    public static void setUser(final String username) {
        _currentUser = username;
    }

    /**
     * Method to create directories for storing game files for the games module.
     */
    public void setUpGameModule() {
        // Create subdirectory for users if not already created
        String user = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data" + FileHelper.FILE_SEPARATOR + "users";
        FileHelper.makeDirectory(user);

        setUpUser(user);

        // Create subdirectory for category files if not already created
        FileHelper.makeDirectory(USER_CATEGORIES);
    }

    /**
     * Method to create directory for storing the given users data if not already
     * created.
     * 
     * @param usersDir the directory where the users files should be stored
     */
    private void setUpUser(final String usersDir) {
        _currentUserDir = usersDir + FileHelper.FILE_SEPARATOR + _currentUser;
        FileHelper.makeDirectory(_currentUserDir);
    }

    /**
     * Used to randomise the categories that the user will be given for questioning.
     */
    public void randomiseCategories() {
        _categoryCollection = new ArrayList<Category>();
        List<Integer> randomFiles = FileHelper.makeRandomList(NUMBER_OF_QUESTIONS, 1, FileHelper.countCategories());
        createAndFillRandomFiles(randomFiles);
    }

    /**
     * Used to fill the random categories with five random questions.
     * 
     * @param randomFiles a list of random numbers for choosing files
     */
    private void createAndFillRandomFiles(final List<Integer> randomFiles) {
        String quinzical = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical" + ".txt";
        File quinzicalFile = new File(quinzical);

        int currentNumberOfGameFiles = FileHelper.countFilesInDirectory(USER_CATEGORIES);

        if (quinzicalFile.isFile()) {
            if (currentNumberOfGameFiles < NUMBER_OF_QUESTIONS) {
                try {
                    BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));

                    String line = null;
                    int categoryCount = 1;
                    int count = 1;
                    int linecount = 0;
                    File copyFile = null;
                    List<String> fileLines = new ArrayList<String>();
                    boolean write = false;

                    while ((line = in.readLine()) != null) {
                        linecount++;
                        // Replace spaces with hypens to name the files
                        if (randomFiles.contains(categoryCount) && count == 1) {
                            _categoryCollection.add(new Category(line));
                            line = line.replace(' ', '-');
                            String fileName = USER_CATEGORIES + FileHelper.FILE_SEPARATOR + line + ".txt";
                            copyFile = new File(fileName);
                            write = true;
                            categoryCount++;
                            count++;
                        } else if (line.isEmpty() || FileHelper.countLinesinFile(quinzicalFile) == linecount) {
                            if (write) {
                                selectLines(copyFile, fileLines);
                                write = false;
                            } else {
                                categoryCount++;
                            }
                            fileLines = new ArrayList<String>();
                            count = 1;
                        } else {
                            line.replace(',', '*');
                            fileLines.add(line);
                            count++;
                        }
                    }
                    in.close();
                } catch (IOException e) {
                    new ExceptionAlert(e);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Used to select five random questions from the chosen category.
     * 
     * @param copyFile
     * @param fileLines
     */
    private void selectLines(final File copyFile, final List<String> fileLines) {
        List<Integer> randomLines = FileHelper.makeRandomList(NUMBER_OF_QUESTIONS, 0, fileLines.size() - 1);
        String line;
        if (!copyFile.exists()) {
            try {
                PrintWriter out = new PrintWriter(copyFile);
                for (int i = 0; i < NUMBER_OF_QUESTIONS; i++) {
                    int lineNumber = randomLines.get(i);
                    line = fileLines.get(lineNumber);
                    line.replace(',', '*');
                    out.println(line);
                }
                out.close();
            } catch (FileNotFoundException e) {
                new ExceptionAlert(e);
                e.printStackTrace();
            }
        }

    }

    /**
     * Method to return the practice categories.
     * 
     * @return List<Category> a list containing all the current categories
     */
    public List<Category> getCategories() {
        if (_categoryCollection.isEmpty()) {
            getCategoriesFromDirectory();
        }
        return _categoryCollection;
    }

    /**
     * Get the categories from a game save.
     */
    private void getCategoriesFromDirectory() {
        File categoriesDir = new File(USER_CATEGORIES);
        for (File file : categoriesDir.listFiles()) {
            String fileName = file.getName();
            _categoryCollection.add(new Category(fileName));
        }
    }

    /**
     * Used to get number of questions left in a category
     * 
     * @param category the category chosen
     * @return int the number of questions left
     */
    public int getCategoriesQuestionNum(final Category category) {
        File categoryFile = new File(USER_CATEGORIES + FileHelper.FILE_SEPARATOR + category.getFilename());
        return FileHelper.countLinesinFile(categoryFile);
    }

    /**
     * Method to reset the game for the current user.
     */
    public void resetGame() {
        ProcessBuilder pb = new ProcessBuilder().command("rm", "-r", _currentUserDir);
        try {
            Process process = pb.start();
            process.waitFor();
        } catch (IOException e) {
            new ExceptionAlert(e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            new ExceptionAlert(e);
            e.printStackTrace();
        }
        setUpGameModule();
        randomiseCategories();
    }

    /**
     * Returns false if no remaining questions, true if there are remaining
     * questions.
     * 
     * @return true if there are questions remaining
     */
    public boolean remainingQuestions() {
        int emptyFiles = 0;
        File userDir = new File(USER_CATEGORIES);
        if (userDir.exists()) {
            if (userDir.isDirectory()) {
                for (File file : userDir.listFiles()) {
                    if (file.length() == 0) {
                        emptyFiles++;
                    }
                }
            }
        }

        return emptyFiles != NUMBER_OF_QUESTIONS;
    }
}
