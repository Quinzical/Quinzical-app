package quinzical.controllers.util;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * This class is used handle one sheep to randomly animate and delete itself
 * when outside the screen
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public abstract class SheepAnimation extends ImageView {

    private static final double MIN_SPEED = 0.1;
    private static final double DEFAULT_SPEED = 0.5;
    private static final double HALF = 0.5;

    private static final int SHEEP_HEIGHT = 150;
    private static final int SHEEP_WIDTH = 150;
    private static final int ROTATION = 360;
    private static final int ROTATION_MAX = 7;
    private static final int ROTATION_MIN = 2;

    private ParallelTransition _animation;

    private double _deltaX = randomFloat(DEFAULT_SPEED, -DEFAULT_SPEED);
    private double _deltaY = randomFloat(DEFAULT_SPEED, -DEFAULT_SPEED);

    private boolean _killed;

    private AnchorPane _pane;

    /**
     * Used to setup animation to ImageView
     * 
     * @param pane
     * @param imageFile
     * @param x
     * @param y
     */
    public SheepAnimation(final AnchorPane pane, final String imageFile, final double x, final double y) {
        super(new Image(imageFile));
        _pane = pane;
        _killed = false;
        setX(x);
        setY(y);

        setFitHeight(SHEEP_HEIGHT);
        setFitWidth(SHEEP_WIDTH);
        setRotate(randomInt(ROTATION, 0));

        RotateTransition rotateTransition = new RotateTransition(
                Duration.seconds(randomFloat(ROTATION_MAX, ROTATION_MIN)), this);
        rotateTransition.setByAngle(ROTATION * ((Math.random() < HALF) ? 1 : -1));
        rotateTransition.setCycleCount(Timeline.INDEFINITE);
        rotateTransition.setInterpolator(Interpolator.LINEAR);

        if (getX() > (_pane.getWidth() + SHEEP_WIDTH)) {
            _deltaX = randomSpeed(0, -DEFAULT_SPEED);
        }
        if (getX() < (-SHEEP_WIDTH * 2)) {
            _deltaX = randomSpeed(DEFAULT_SPEED, 0);
        }
        if (getY() > (_pane.getHeight() + SHEEP_HEIGHT)) {
            _deltaX = randomSpeed(0, -DEFAULT_SPEED);
        }
        if (getY() < (-SHEEP_HEIGHT * 2)) {
            _deltaX = randomSpeed(DEFAULT_SPEED, 0);
        }

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent t) {
                setX(getX() + _deltaX);
                setY(getY() + _deltaY);

                final boolean atRightBorder = getX() >= (_pane.getWidth());
                final boolean atLeftBorder = getX() <= (-SHEEP_WIDTH);
                final boolean atBottomBorder = getY() >= (_pane.getHeight());
                final boolean atTopBorder = getY() <= (-SHEEP_HEIGHT);
                if (!_killed) {
                    if (atRightBorder || atLeftBorder || atBottomBorder || atTopBorder) {
                        _killed = true;
                        kill();
                        _animation.stop();
                    }
                }

            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);

        _animation = new ParallelTransition(rotateTransition, loop);
        _animation.play();
    }

    /**
     * Used to kill itself when it is offscreen
     */
    protected abstract void kill();

    private int randomInt(final int maxInt, final int minInt) {
        return (int) (Math.random() * (maxInt - minInt) + minInt);
    }

    private double randomSpeed(final double maxFloat, final double minFloat) {
        double random = 0;
        while (Math.abs(random) < MIN_SPEED) {
            random = randomFloat(maxFloat, minFloat);
        }
        return random;
    }

    private double randomFloat(final double maxFloat, final double minFloat) {
        return (Math.random() * ((maxFloat - minFloat) + 1)) + minFloat;
    }
}
