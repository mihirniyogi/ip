package bob;

import java.io.IOException;
import bob.command.Command;
import bob.command.WrongCommandException;
import bob.parser.Parser;
import bob.ui.Ui;

/**
 * This class is the entry point of the Bob program.
 */
public class Bob {

    /**
     * Acts as main entry point of the application.
     * <p>
     * This method initializes the user interface, prints the welcome message,
     * and enters an infinite loop to read and process user commands.
     * It handles various exceptions, such as invalid commands and input errors,
     * and provides feedback to the user through the {@link Ui} class.
     * </p>
     */
    public static void main(String[] args) {

        Ui ui = new Ui();
        ui.printWelcome();

        while (true) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                c.execute(ui);
                
            } catch (WrongCommandException e) {
                ui.print(e.getMessage(), "Please try again!");
            } catch (IndexOutOfBoundsException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (NumberFormatException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (IOException e) {
                ui.print("Uh oh! Bob says...I'm sorry, there was an error saving the task :(");
            }   
        }
    }
}