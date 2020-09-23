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
 * @Author Cheng-Zhen Yang
 */
public class PracticeCategoryButton extends Button {

    private final SceneManager _sceneManager = SceneManager.getInstance();

    /**
     * TODO need to replace with Category class
     * 
     * @param name
     */
    public PracticeCategoryButton(Category category) {
        super(category.toString());
        // set size
        setPrefWidth(220);
        setPrefHeight(70);
        // set font
        setStyle("-fx-font-size:20");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                QuestionModel _questionModel = QuestionModel.getInstance();
                _questionModel.setPractice(true);
                _questionModel.setCategory(category);
                _sceneManager.switchScene(Scenes.QUESTION);
            }
        });
    }
}
