package quinzical.controllers.util;

import quinzical.util.FileHelper;

/**
 * Enum for each sheep image file.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public enum Sheep {

    /** Blue sheep */
    BLUE("blue.png"),
    /** Cyan sheep */
    CYAN("cyan.png"),
    /** Green sheep */
    GREEN("green.png"),
    /** Orange sheep */
    ORANGE("orange.png"),
    /** Pink sheep */
    PINK("pink.png"),
    /** Red sheep */
    RED("red.png"),
    /** White sheep */
    WHITE("white.png"),
    /** Yellow sheep */
    YELLOW("yellow.png");

    private final String _filename;

    private static final String PATH = FileHelper.FILE_SEPARATOR + "quinzical" + FileHelper.FILE_SEPARATOR
            + "resources" + FileHelper.FILE_SEPARATOR + "images" + FileHelper.FILE_SEPARATOR + "sheeps" + FileHelper.FILE_SEPARATOR;

    Sheep(final String filename) {
        _filename = filename;
    }

     /**
     * Used to return the image name of the sheep
     * 
     * @return String filename
     */
    public String getImageName() {
        return _filename;
    }

    /**
     * Used to return the filename of the sheep
     * 
     * @return String filename
     */
    public String getFilename() {
        return PATH + _filename;
    }

    /**
     * Used to return the equivalent sheep object from a filename
     * 
     * @param filename
     * @return Sheep the sheep that relates to the file
     */
    public static Sheep fromString(final String filename) {
        for (Sheep sheep : Sheep.values()) {
            if (sheep._filename.equals(filename)) {
                return sheep;
            }
        }
        return null;
    }
}
