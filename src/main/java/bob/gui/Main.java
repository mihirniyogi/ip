package bob.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label();
        label.setText("Hello, Bob!");

        Scene scene = new Scene(label);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bob");
        primaryStage.show();
    }   
}
