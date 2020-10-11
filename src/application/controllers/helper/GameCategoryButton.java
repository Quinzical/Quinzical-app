package application.controllers.helper;

import application.helper.SceneManager;
import application.helper.SceneManager.Scenes;
import application.models.helper.Category;
import application.models.question.QuestionModel;
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
     */
    public GameCategoryButton(final Category category, final String value) {
        super("$" + value);
        // set size
        setPrefWidth(DEFAULT_WIDTH + Integer.valueOf(value) * CHANGE_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);
        // set font
        setStyle("-fx-font-size:16; -fx-padding: 0 10 0 10; -fx-border-insets: 0 10 0 10;"
                + " -fx-background-insets: 0 10 0 10;");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {

                QuestionModel questionModel = QuestionModel.getInstance();
                questionModel.setPractice(false);
                questionModel.setCategory(category);
                questionModel.setQuestionValue(value);
                _sceneManager.unloadScene();
                _sceneManager.switchScene(Scenes.GAME_QUESTION);
            }
        });
    }
}
