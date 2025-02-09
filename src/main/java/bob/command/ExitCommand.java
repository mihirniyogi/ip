package bob.command;

import bob.util.Formatter;

/**
 * This class represents a command to exit the program.
 */
public class ExitCommand extends Command {
    @Override
    public String execute() {
        return Formatter.format("Thank you and goodbye!");
    }
}
