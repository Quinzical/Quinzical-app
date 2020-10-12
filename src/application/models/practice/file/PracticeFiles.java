package application.models.practice.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import application.models.helper.Category;
import application.controllers.helper.ExceptionAlert;
import application.helper.FileHelper;

/**
 * This class is used to delegate tasks to different classes who carry out tasks
 * for the function of the practice module based on what the user wants to do.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class PracticeFiles {

    private static final String CATEGORIES_FOLDER = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data"
            + FileHelper.FILE_SEPARATOR + "categories";
    private List<Category> _categoryCollection;

    /**
     * Creates a PracticeFiles instance
     */
    public PracticeFiles() {
        _categoryCollection = new ArrayList<Category>();
    }

    /**
     * Used to get the static practice category directory.
     * 
     * @return userCategories
     */
    public static String getCategoryFolder() {
        return CATEGORIES_FOLDER;
    }

    /**
     * Method to copy categories and their relevant questions and answers into
     * separate files from the quinzical file.
     */
    public void copyCategories() {
        String quinzical = FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical" + ".txt";
        File quinzicalFile = new File(quinzical);
        if (quinzicalFile.isFile()) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(quinzicalFile));
                PrintWriter out = null;

                String line = null;
                int count = 1;
                File copyFile = null;

                while ((line = in.readLine()) != null) {
                    // Replace spaces with hypens to name the files
                    if (count == 1) {
                        _categoryCollection.add(new Category(line));
                        line = line.replace(' ', '-');
                        String copyName = CATEGORIES_FOLDER + FileHelper.FILE_SEPARATOR + line + ".txt";
                        copyFile = new File(copyName);
                        out = new PrintWriter(copyFile);
                        count++;
                    } else if (line.isEmpty()) {
                        count = 1;
                        out.close();
                    } else {
                        count++;
                        out.println(line);
                    }
                }
                in.close();
                out.close();
            } catch (IOException e) {
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
        return _categoryCollection;
    }
}
