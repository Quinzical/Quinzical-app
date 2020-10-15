package application.controllers.helper;

import application.models.helper.Category;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * This class is for Button styled for Practice Category Button on PracticeMenu
 *
 * @author Maggie Pedersen
 * @author Cheng-Zhen Yang
 */
public abstract class CategoryButton extends Button {
    private static final int DEFAULT_HEIGHT = 70;

    private Category _category;

    /**
     * Used to setup CategoryButton
     * 
     * @param category
     */
    public CategoryButton(final Category category) {
        super(category.toString());
        _category = category;
        // set size
        setPrefHeight(DEFAULT_HEIGHT);
        setMaxWidth(Double.MAX_VALUE);
        // set font
        setStyle("-fx-font-size:20");

        // handle button on press
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent actionEvent) {
                onAction();
            }
        });
    }

    /**
     * Used when button is pressed
     */
    public abstract void onAction();

    /**
     * @return Category return the _category
     */
    public Category getCategory() {
        return _category;
    }
}
