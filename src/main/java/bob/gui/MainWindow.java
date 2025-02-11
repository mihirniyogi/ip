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

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    private static final String WELCOME_MESSAGE = "Hello, I'm Bob. How can I help you today?";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    /**
     * Method triggered by 'ENTER' keypress or Send button)
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != "" : "Input should not be empty";
        String response = getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialogBox(input));
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(response));
        userInput.clear();
    }

    /**
     * Returns the response from Bob based on the user input.
     *
     * @param input user's input.
     * @return String response from Bob.
     */
    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command should not be null";

            String output = c.execute();
            assert output != null : "Output should not be null";

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

    /**
     * Note that method is auto-called,
     * We are doing additional setup here.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty()); // Auto-scroll to the bottom
        dialogContainer.getChildren().add(DialogBox.getBobDialogBox(WELCOME_MESSAGE)); // Starting message
    }
}
