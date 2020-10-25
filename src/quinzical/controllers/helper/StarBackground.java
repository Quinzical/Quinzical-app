package quinzical.controllers.helper;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This class is used for animating stars in the background using a template
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public final class StarBackground {

    private static final int TRANSLATION_X = 1280;
    private static final int FINAL_SPEED = 10000;

    private StarBackground() {
    }

    /**
     * Used to animate 3 star background at different speeds
     * 
     * @param bg1
     * @param bg2
     * @param bg3
     */
    public static void animate(final ImageView bg1, final ImageView bg2, final ImageView bg3) {
        backgroundAnimation(FINAL_SPEED, bg1);
        backgroundAnimation(FINAL_SPEED * 2, bg2);
        backgroundAnimation(FINAL_SPEED * 3, bg3);
    }

    /**
     * Used to aniamte a single background
     * 
     * @param durationTime
     * @param background
     */
    public static void backgroundAnimation(final double durationTime, final ImageView background) {
        Duration duration = Duration.millis(durationTime);
        TranslateTransition transition = new TranslateTransition(duration, background);
        transition.setByX(TRANSLATION_X);
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setInterpolator(Interpolator.LINEAR);
        transition.play();
    }
}
