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
        getStyleClass().add("category");
        setStyle("-fx-font-size:22;");

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
