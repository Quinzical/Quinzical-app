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
 * @Author Cheng-Zhen Yang
 */
public class GameCategoryButton extends Button {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * Used to setup GameCategoryButton
     * 
     * @param category
     * @param string
     */
    public GameCategoryButton(Category category, String value) {
        super("$"+value);
        // set size
        setPrefWidth(145 + Integer.valueOf(value)*0.15);
        setPrefHeight(30);
        // set font
        setStyle("-fx-font-size:16; -fx-padding: 0 10 0 10; -fx-border-insets: 0 10 0 10; -fx-background-insets: 0 10 0 10;");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                QuestionModel _questionModel = QuestionModel.getInstance();
                _questionModel.setPractice(false);
                _questionModel.setCategory(category);
                _questionModel.setQuestionValue(value);
                _sceneManager.unloadScene();
                _sceneManager.switchScene(Scenes.QUESTION);
            }
        });
    }
}
