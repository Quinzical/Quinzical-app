package quinzical.controllers.util.buttons;

import quinzical.util.socket.SocketIO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Lobby Button for choosing lobby
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class LobbyButton extends Button {

    private final SocketIO _socket = SocketIO.getInstance();

    private static final int DEFAULT_WIDTH = 220;
    private static final int DEFAULT_HEIGHT = 70;

    /**
     * Used to setup Lobby Button with join feature
     * 
     * @param code
     */
    public LobbyButton(final String code) {
        super(code.toString());
        // set size
        setPrefWidth(DEFAULT_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);
        // set font
        getStyleClass().add("category");
        setStyle("-fx-font-size:32;");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                _socket.joinRoom(code);
            }
        });
    }
}
