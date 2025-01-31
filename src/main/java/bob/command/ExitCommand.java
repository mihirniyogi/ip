package bob.command;

import bob.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.print("Thank you and goodbye!");
        ui.closeScanner();
        System.exit(0);
    }
}
