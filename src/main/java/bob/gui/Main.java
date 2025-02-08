package bob.gui;

import bob.Bob;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Bob bob = new Bob(); 

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();  
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            loader.<MainWindow>getController().setBob(this.bob);
            primaryStage.setTitle("Bob");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
}
