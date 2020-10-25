package quinzical.controllers.online;

import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.socket.SocketIO;
import quinzical.util.processes.BasicTimerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * 
 */
public class OnlineQuestionController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();

    private static final int TIMER_DIVIDER = 1000;

    private BasicTimerThread _timerThread;

    @FXML
    private Label _timer;

    @FXML
    private Label _qualifier;

    @FXML
    private Label _question;

    @FXML
    private TextField _answerField;

    @FXML
    private Button _submitBtn;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;


    /**
     * initialize with OnlineQuestion.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        // _timer.setText(_socket.get());
        _qualifier.setText(_socket.getQualifier());
        _question.setText(_socket.getQuestion());

        _timerThread = new BasicTimerThread(_timer, _socket.getRoom().getInt("timer") / TIMER_DIVIDER);
        _timerThread.start();

        if (!_socket.isPlaying()) {
            _answerField.setDisable(true);
            _answerField.setText("Spectating");
            _submitBtn.setDisable(true);
        }
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _socket.disconnect();
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handleSubmit(final ActionEvent event) {
        _socket.sendAnswer(_socket.getRoom().getString("code"), _answerField.getText());
        _answerField.setDisable(true);
        _answerField.setText("Waiting...");
        _submitBtn.setDisable(true);
    }

}
