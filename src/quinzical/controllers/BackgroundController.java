package quinzical.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;

/**
 * This class used to animate Background Stars
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class BackgroundController {

    @FXML
    private AnchorPane _background;

    @FXML
    private ImageView _background1;

    @FXML
    private ImageView _background2;

    @FXML
    private ImageView _background3;

    /**
     * initialize with OpeningMenu.fxml
     */
    public void initialize() {
        StarBackground.animate(_background1, _background2, _background3);
        SceneManager.getInstance().setAnchor(_background);
    }
}
