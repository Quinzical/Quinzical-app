package application.controllers;

import application.controllers.helper.StarBackground;

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
    }
}
