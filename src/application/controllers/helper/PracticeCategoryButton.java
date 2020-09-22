package application.controllers.helper;

import application.controllers.SceneManager;
import application.controllers.SceneManager.Scenes;
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
    public PracticeCategoryButton(String name) {
        super(name);
        // set size
        setPrefWidth(220);
        setPrefHeight(70);
        // set font
        setStyle("-fx-font-size:20");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // TODO add question model
                _sceneManager.switchScene(Scenes.QUESTION);
            }
        });
    }
}
