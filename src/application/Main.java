package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
    private Scene _menu;

    @Override
    public void start(final Stage primaryStage) throws Exception {

        // Initiate the first Scene - MainView
        Parent root = FXMLLoader.load(getClass().getResource("/application/resources/HomeMenu.fxml"));
        _menu = new Scene(root, 1280, 800);
        _menu.getStylesheets().add("/application/resources/application.css");

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(final WindowEvent t) {
                // Save game?
            }
        });
        primaryStage.setTitle("Quinzical");
        primaryStage.getIcons().add(new Image("/application/resources/images/darklogo.png"));
        primaryStage.setScene(_menu);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch();
    }
}
