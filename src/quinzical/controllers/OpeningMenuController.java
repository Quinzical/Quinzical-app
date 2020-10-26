package quinzical.controllers;

import quinzical.controllers.util.alerts.ConfirmAlert;
import quinzical.controllers.util.Sheep;
import quinzical.controllers.util.StarBackground;
import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.JWTStore;
import quinzical.util.models.LoginModel;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * This class is the OpeningMenu controller in a MVC design.
 * 
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class OpeningMenuController {

    private final SceneManager _sceneManager = SceneManager.getInstance();
    private final LoginModel _login = LoginModel.getInstance();

    private Sheep[] _images = { Sheep.BLUE, Sheep.CYAN, Sheep.GREEN, Sheep.ORANGE, Sheep.PINK, Sheep.RED, Sheep.WHITE,
            Sheep.YELLOW };
    private static final int NUMBER_OF_SHEEP = 4;
    private static final int SHEEP_HEIGHT = 200;
    private static final int SHEEP_WIDTH = 200;

    @FXML
    private AnchorPane _pane;

    @FXML
    private AnchorPane _sheepPane;

    @FXML
    private Label _usernameLabel;

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
        _usernameLabel.setText(_login.getUsername());
        _usernameLabel.getStyleClass().add("logingreen");
        StarBackground.animate(_background1, _background2, _background3);
        Platform.runLater(() -> {
            animateRandomSheep();
        });
        
    }

    /**
     * Used to handle customise button
     * 
     * @param event
     */
    @FXML
    private void handleCustomiseButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CUSTOMISE_MENU);
    }

    /**
     * Used to handle help button
     * 
     * @param event
     */
    @FXML
    private void handleHelpButton(final ActionEvent event) {
        _sceneManager.switchScene(SceneManager.Scenes.HELP_SCREEN);
    }

    /**
     * Used to handle info button
     * 
     * @param event
     */
    @FXML
    private void handleInfoButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.INFO);
    }

    /**
     * Used to handle local play button
     * 
     * @param event
     */
    @FXML
    private void handleLocalButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.HOME_MENU);
    }

    /**
     * Used to handle chat button
     * 
     * @param event
     */
    @FXML
    private void handleMessageButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.CHAT_SCREEN);
    }

    /**
     * Used to handle online play button
     * 
     * @param event
     */
    @FXML
    private void handleOnlineButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.ONLINE_MENU);
    }

    /**
     * Used to handle settings button
     * 
     * @param event
     */
    @FXML
    private void handleSettingsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.SETTINGS_MENU);
    }

    /**
     * Used to handle stats button
     * 
     * @param event
     */
    @FXML
    private void handleStatsButton(final ActionEvent event) {
        _sceneManager.switchScene(Scenes.STATS_SCREEN);
    }

    /**
     * Used to handle exit button
     * 
     * @param event
     */
    @FXML
    private void handleExit(final ActionEvent event) {
        new ConfirmAlert("Quit the Game") {
            @Override
            protected void handleConfirm() {
                _sceneManager.close();
            }
        };
    }

    /**
     * Used to handle logout button
     * 
     * @param event
     */
    @FXML
    private void handleLogout(final ActionEvent event) {
        _sceneManager.unloadAllScenes();
        _sceneManager.switchScene(Scenes.LOGIN_SCREEN);
        JWTStore jwtStore = new JWTStore();
        jwtStore.setJWT("");
    }

    /**
     * Used to animate sheep of random colours, on the screen.
     */
    private void animateRandomSheep() {

        int[] randomValues = new int[NUMBER_OF_SHEEP];

        for (int i = 0; i < NUMBER_OF_SHEEP; i++) {
            // Create random number between 0 and 7 to get the colours of the sheep
            randomValues[i] = randomNumberGenerator(_images.length - 1, 0);
        }

        for (int j = 0; j < randomValues.length; j++) {
            ImageView sheep = new ImageView(new Image(_images[randomValues[j]].getFilename()));
            sheep.setFitHeight(SHEEP_HEIGHT);
            sheep.setFitWidth(SHEEP_WIDTH);

            _sheepPane.getChildren().add(sheep);
            System.out.println(_pane.getWidth());

            sheep.setX(randomSpeed(_pane.getWidth(), 0));
            sheep.setY(randomSpeed(_pane.getHeight(), 0));

            RotateTransition rotateTransition = new RotateTransition(Duration.millis(15000), sheep);
            rotateTransition.setByAngle(360);
            rotateTransition.setCycleCount(Timeline.INDEFINITE);

            final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

                double deltaX = randomSpeed(2, -2);
                double deltaY = randomSpeed(2, -2);

                @Override
                public void handle(final ActionEvent t) {
                    sheep.setX(sheep.getX() + deltaX);
                    sheep.setY(sheep.getY() + deltaY);

                    final boolean atRightBorder = sheep.getX() >= (_pane.getWidth());
                    final boolean atLeftBorder = sheep.getX() <= (0);
                    final boolean atBottomBorder = sheep.getY() >= (_pane.getHeight());
                    final boolean atTopBorder = sheep.getY() <= (0);

                    System.out.println(sheep.getX());

                    if (atRightBorder || atLeftBorder) {
                        deltaX = randomSpeed(2, -2);
                        deltaY = randomSpeed(2, -2);
                    }
                    if (atBottomBorder || atTopBorder) {
                        deltaX = randomSpeed(2, -2);
                        deltaY = randomSpeed(2, -2);
                    }
                }
            }));

            loop.setCycleCount(Timeline.INDEFINITE);

            ParallelTransition pt = new ParallelTransition(rotateTransition, loop);
            pt.play();
        }
    }

    private int randomNumberGenerator(int maxNumber, int minNumber) {
        return (int) (Math.random() * ((maxNumber - minNumber) + 1)) + minNumber;
    }

    private double randomSpeed(double maxFloat, double minFloat) {
        double random = 0;
        while (Math.abs(random) < 0.4) {
            random = (Math.random() * ((maxFloat - minFloat) + 1)) + minFloat;
        }
        return random;
    }
}
