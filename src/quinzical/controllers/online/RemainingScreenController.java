package quinzical.controllers.online;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;


import quinzical.util.socket.SocketIO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

/**
 * This class is used to show remaining users in a online game
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class RemainingScreenController {

    private final SocketIO _socket = SocketIO.getInstance();

    @FXML
    private TilePane _users;

    @FXML
    private Button _nextBtn;

    /**
     * initialize with RemainingScreen.fxml
     */
    public void initialize() {
        updateUsers();
        if (!_socket.getRoom().getString("host").equals(_socket.getSocketID())) {
            _nextBtn.setDisable(true);
            _nextBtn.setText("Waiting for Host");
        }
    }

    /**
     * Update users on the screen inside remaining screen on update
     */
    private void updateUsers() {
        JSONObject room = _socket.getRoom();
        JSONArray users = room.getJSONArray("correct");
        HashMap<String, String> allUsers = _socket.getUsers();
        _users.getChildren().clear();

        for (int i = 0; i < users.length(); i++) {
            String socketID = users.getString(i);
            Label user = new Label(allUsers.get(socketID));

            if (socketID.equals(_socket.getSocketID())) {
                user.getStyleClass().add("logingreen");
            } else {
                user.getStyleClass().add("login");
            }

            _users.getChildren().add(user);
        }
    }

}
