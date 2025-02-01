package bob.command;

import bob.ui.Ui;

/**
 * This class represents a command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.print("Thank you and goodbye!");
        ui.closeScanner();
        System.exit(0);
    }
}
