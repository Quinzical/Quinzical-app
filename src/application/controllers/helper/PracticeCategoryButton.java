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
public class PracticeCategoryButton extends Button {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    private static final int DEFAULT_WIDTH = 220;
    private static final int DEFAULT_HEIGHT = 70;

    /**
     * Used to setup PracticeCategoryButton
     * 
     * @param category
     */
    public PracticeCategoryButton(final Category category) {
        super(category.toString());
        // set size
        setPrefWidth(DEFAULT_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);
        // set font
        setStyle("-fx-font-size:20; -fx-text-fill: white; -fx-border-width: 2 2 2 2; -fx-border-color: white; -fx-border-radius: 10;");
        getStyleClass().add(".button:hover");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {

                QuestionModel questionModel = QuestionModel.getInstance();
                questionModel.setPractice(true);
                questionModel.setCategory(category);
                _sceneManager.switchScene(Scenes.PRACTICE_QUESTION);
            }
        });
    }
}
