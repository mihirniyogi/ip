package bob.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private static final Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/user.png"));
    private static final Image bobImage = new Image(DialogBox.class.getResourceAsStream("/images/bob.png"));

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();            
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT); 
    }

    public static DialogBox getUserDialog(String text) {
        return new DialogBox(text, userImage);
    }

    public static DialogBox getBobDialogBox(String text) {
        var db = new DialogBox(text, bobImage);
        db.flip();
        return db;
    }
}
