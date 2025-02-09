package bob.gui;

import java.io.IOException;

import bob.command.Command;
import bob.command.ExitCommand;
import bob.command.WrongCommandException;
import bob.parser.Parser;
import bob.util.Formatter;
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

    private static final String WELCOME_MESSAGE = "Hello, I'm Bob. How can I help you today?";

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialogBox(input));
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(response));
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String output = c.execute();
            if (c instanceof ExitCommand) {
                System.exit(0);
            }
            return output;
        } catch (WrongCommandException e) {
            return Formatter.format(e.getMessage(), "Please try again!");
        } catch (IndexOutOfBoundsException e) {
            return Formatter.format("Uh oh! Bob says...I'm sorry, there is no such task :(");
        } catch (NumberFormatException e) {
            return Formatter.format("Uh oh! Bob says...I'm sorry, there is no such task :(");
        } catch (IOException e) {
            return Formatter.format("Uh oh! Bob says...I'm sorry, there was an error saving the task :(");
        }
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(WELCOME_MESSAGE));
    }
}
