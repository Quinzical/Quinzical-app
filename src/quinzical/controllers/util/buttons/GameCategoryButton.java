package quinzical.controllers.util.buttons;

import quinzical.util.SceneManager;
import quinzical.util.SceneManager.Scenes;
import quinzical.util.models.util.Category;
import quinzical.util.models.QuestionModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * This class is for Button styled for Practice Category Button on PracticeMenu
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public class GameCategoryButton extends Button {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private static final int DEFAULT_WIDTH = 145;
    private static final int DEFAULT_HEIGHT = 30;
    private static final double CHANGE_WIDTH = 0.15;

    /**
     * Used to setup GameCategoryButton
     * 
     * @param category
     * @param value
     * @param state    0=incorrect, 1=correct
     */
    public GameCategoryButton(final Category category, final String value, final int state) {
        super("$" + value);
        // set size
        setPrefWidth(DEFAULT_WIDTH + Integer.valueOf(value) * CHANGE_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);
        // set color and style
        String color;
        switch (state) {
            case 0:
                color = "-fx-background-color: #ff8c8c;";
                break;
            case 1:
                color = "-fx-background-color: #4efc60;";
                break;
            default:
                color = "";
                break;
        }
        setStyle("-fx-font-size:16; -fx-padding: 10 10 10 10; -fx-border-insets: 0 10 0 10;"
                + " -fx-background-insets: 0 10 0 10;" + "-fx-font-size:20; -fx-text-fill: white;"
                + " -fx-border-width: 2 2 2 2; -fx-border-color: white; -fx-border-radius: 10;" + color);
        getStyleClass().add("category");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {

                QuestionModel questionModel = QuestionModel.getInstance();
                questionModel.setPractice(false);
                questionModel.setCategory(category);
                questionModel.setQuestionValue(value);
                setDisable(true);
                _sceneManager.unloadScene();
                _sceneManager.switchScene(Scenes.GAME_QUESTION);
            }
        });
    }
}
