package quinzical.controllers.online;

import quinzical.controllers.util.StarBackground;
import quinzical.util.socket.SocketIO;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * This class is eject screen for online game play.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class EjectScreenController {

    private final SocketIO _socket = SocketIO.getInstance();
    private static final double FRAME_TIME = 50;

    @FXML
    private AnchorPane _anchor;

    @FXML
    private Label _message;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with EjectScreen.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _message.setText("");
        Platform.runLater(() -> {
            if (_socket.getCorrect()) {
                animateLabel("Correct, Answer is " + _socket.getAnswer(), _message);
            } else {
                animateLabel("Incorrect, Answer is " + _socket.getAnswer(), _message);
            }

        });
    }

    /**
     * animate label
     * 
     * @param string
     * @param label
     */
    public void animateLabel(final String string, final Label label) {
        final IntegerProperty counter = new SimpleIntegerProperty(0);
        int length = string.length();
        Timeline line = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.millis(FRAME_TIME), event -> {
            if (counter.get() > length) {
                line.stop();
            } else {
                label.setText(string.substring(0, counter.get()));
                counter.set(counter.get() + 1);
            }
        });

        line.getKeyFrames().add(frame);
        line.setCycleCount(Animation.INDEFINITE);
        line.play();
    }
}
