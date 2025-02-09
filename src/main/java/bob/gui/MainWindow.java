package bob.gui;

import bob.Bob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    private Bob bob;

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = input; // TODO: To be changed
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, bobImage));
        userInput.clear();
    }

    public void setBob(Bob b) {
        this.bob = b;
    }
}
