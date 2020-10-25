package quinzical.models.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class FileHashCheck {

    private final Path _hashPath = Paths
            .get(FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "data" + FileHelper.FILE_SEPARATOR + "hash.txt");

    private final Path _db = Paths
            .get(FileHelper.CURRENT_DIR + FileHelper.FILE_SEPARATOR + "quinzical.txt");

    /**
     * Create ScoreTracker instance
     */
    public FileHashCheck() {
    }

    /**
     * Check if file is changed
     * 
     * @return bool
     */
    public boolean checkChange() {
        String actual = "";

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(_db));
            byte[] digest = md.digest();
            actual = byteArrayToHex(digest);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (!Files.exists(_hashPath)) {
            setHash(actual);
            return true;
        }

        String expected = getHash().toLowerCase();
        if (!actual.equals(expected)) {
            setHash(actual);
            return true;
        }

        return false;
    }

    /**
     * Get hash from file
     * 
     * @return hash
     */
    private String getHash() {
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
     * Set hash
     * 
     * @param hash
     */
    private void setHash(final String hash) {
        try {
            Files.write(_hashPath, hash.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            new ExceptionAlert(e);
            e.printStackTrace();
        }
    }

    /**
     * byteArray to Hex string
     * 
     * @param a bytes to convert
     * @return hex string
     */
    private String byteArrayToHex(final byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
