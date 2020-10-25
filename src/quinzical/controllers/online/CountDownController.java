package quinzical.controllers.online;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.socket.SocketIO;
import quinzical.util.processes.BasicTimerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * This class is used for a 3 sec countdown before online question
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class CountDownController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();

    private BasicTimerThread _timerThread;

    @FXML
    private Label _countDown;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with CountDown.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        _timerThread = new BasicTimerThread(_countDown, 3);
        _timerThread.start();
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _socket.disconnect();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

}
