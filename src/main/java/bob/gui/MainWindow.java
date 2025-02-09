package bob.gui;

import bob.Bob;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

    private Bob bob;

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = input;
        dialogContainer.getChildren().add(DialogBox.getUserDialogBox(input));
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(response));
        userInput.clear();
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBob(Bob b) {
        this.bob = b;
    }
}
