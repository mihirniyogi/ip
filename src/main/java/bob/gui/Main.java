package bob.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();  
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);            
            primaryStage.setTitle("Bob");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
