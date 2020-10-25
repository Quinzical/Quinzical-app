package quinzical.models.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import quinzical.controllers.helper.ExceptionAlert;
import quinzical.helper.FileHelper;

/**
 * This class is used to keep a track of the current users score from the games
 * module.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class JWTStore {

    private final Path _hashPath = Paths
            .get(FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data" + FileHelper.FILE_SEPARATOR + "auth.txt");

    /**
     * Create Auth state that saves JWT instance
     */
    public JWTStore() {
    }

    /**
     * Get jwt from file
     * 
     * @return hash
     */
    public String getJWT() {
        try {
            List<String> contents = Files.readAllLines(_hashPath);
            return contents.get(0);
        } catch (IOException e) {
            new ExceptionAlert(e);
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
        return "";
    }

    /**
     * Set jwt
     * 
     * @param jwt
     */
    public void setJWT(final String jwt) {
        try {
            Files.write(_hashPath, jwt.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            new ExceptionAlert(e);
            e.printStackTrace();
        }
    }
}
