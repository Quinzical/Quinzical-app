package application.models;

/**
 * This class is used to store and calculate data for the settings page that the
 * user sees.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class SettingsModel {

    /**
     * Default speed of espeak 175
     */
    public static final int DEFAULT_SPEED = 175;
    /**
     * Default volume of espeak 100
     */
    public static final int DEFAULT_VOLUME = 100;

    private static final int MIN_SPEED = 80;
    private static final int MAX_SPEED = 440;
    private static final double ESPEAK_DIVIDER = 100.0;

    // Sets speed from range of 80 to 440. The default is 175.
    private static int _espeakSpeed = DEFAULT_SPEED;

    // Sets amplitude (volume) in a range of 0 to 200. The default is 100.
    private static int _espeakVolume = DEFAULT_VOLUME;

    private SettingsModel() {
    }

    /**
     * Sets the speed in words-per-minute (approximate values for the default
     * English voice, others may differ slightly). The default value is 175. I
     * generally use a faster speed of 260. The lower limit is 80. The upper limit
     * is 440 for calculation reasons.
     * 
     * @param sliderSpeed the speed between 0 and 100 that the user has selected on
     *                    the view
     */
    public static void setEspeakSpeed(final int sliderSpeed) {
        double percentage = (sliderSpeed / ESPEAK_DIVIDER);
        _espeakSpeed = (int) ((percentage * (MAX_SPEED - MIN_SPEED)) + MIN_SPEED);
    }

    /**
     * Returns the speed in the espeak range between 80 and 440.
     * 
     * @return int
     */
    public static int getEspeakSpeed() {
        return _espeakSpeed;
    }

    /**
     * Sets the volume based in the espeakRange, between 0 and 200.
     * 
     * @param sliderVolume
     */
    public static void setEspeakVolume(final int sliderVolume) {
        _espeakVolume = 2 * sliderVolume;
    }

    /**
     * Returns the speed in the espeak range between 0 and 200.
     * 
     * @return int
     */
    public static int getEspeakVolume() {
        return _espeakVolume;
    }

}
