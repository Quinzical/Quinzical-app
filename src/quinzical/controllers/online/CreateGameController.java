package quinzical.controllers.online;

import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.LoginModel;
import quinzical.util.socket.SocketIO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * This class is used to create an online game using settings on the scene
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class CreateGameController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();
    private final SocketIO _socket = SocketIO.getInstance();
    private static final int DEFAULT_TIMER = 10;

    private int _timer = DEFAULT_TIMER;
    private boolean _internationalQuestion = false;

    @FXML
    private Slider _timerSlider;

    @FXML
    private Label _timerValue;

    @FXML
    private Button _newZealand;

    @FXML
    private Button _international;

    /**
     * initialize with CreateGame.fxml
     */
    public void initialize() {
        _international.setDisable(true);

        _timerSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(final ObservableValue<? extends Number> observable, final Number oldValue,
                    final Number newValue) {
                _timer = newValue.intValue();
                _timerValue.setText(String.valueOf(newValue.intValue()));
            }
        });
    }

    @FXML
    private void handleBack(final ActionEvent event) {
        _sceneManager.cleanSwitchScene(Scenes.ONLINE_MENU);
    }

    @FXML
    private void handleConfirm(final ActionEvent event) {
        _socket.createRoom(_login.getUsername(), _timer, _internationalQuestion);
    }

}
