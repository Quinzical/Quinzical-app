package quinzical.controllers;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import quinzical.models.helper.SplashModel;

/**
 * This class is the SplashScreen controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class SplashScreenController {

    private static final double ANGLE_360 = 360;

    @FXML
    private ImageView _logo;

    @FXML
    private ImageView _outerSpinner;

    @FXML
    private ImageView _innerSpinner;

    private SplashModel _splashModel = SplashModel.getInstance();

    /**
     * initialize with SplashScreen.fxml
     */
    public void initialize() {
        // Duration = 2.5 seconds
        RotateTransition outer = new RotateTransition(Duration.seconds(3), _outerSpinner);
        outer.setFromAngle(0);
        outer.setToAngle(ANGLE_360);
        outer.setCycleCount(Animation.INDEFINITE);
        outer.setInterpolator(Interpolator.EASE_BOTH);
        outer.play();

        RotateTransition inner = new RotateTransition(Duration.seconds(2), _innerSpinner);
        inner.setFromAngle(0);
        inner.setToAngle(-ANGLE_360);
        inner.setCycleCount(Animation.INDEFINITE);
        inner.setInterpolator(Interpolator.EASE_BOTH);
        inner.play();

        FadeTransition logo = new FadeTransition(Duration.seconds(1), _logo);
        logo.setFromValue(1);
        logo.setToValue(0);
        logo.setAutoReverse(true);
        logo.setCycleCount(Animation.INDEFINITE);
        SequentialTransition seqTransition = new SequentialTransition(new PauseTransition(Duration.seconds(1)), logo);
        seqTransition.play();

        _splashModel.doMethod();
    }
}
