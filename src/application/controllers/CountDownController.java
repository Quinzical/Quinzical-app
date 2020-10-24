package application.controllers;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.socket.SocketIO;
import application.processes.BasicTimerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    /**
     * initialize with CountDown.fxml
     */
    public void initialize() {
        _timerThread = new BasicTimerThread(_countDown, 3);
        _timerThread.start();
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _socket.disconnect();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

}
