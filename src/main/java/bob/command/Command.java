package bob.command;

/**
 * This class represents a command. It is an abstract class that
 * contains an abstract method {@code execute(Ui)} that is implemented
 * by its <i>many</i> subclasses.
 */
public abstract class Command {
    /**
     * Executes the command.
     * Uses the {@code Ui} object to interact with the user, if applicable.
     *
     * @param ui instance.
     */
    public abstract String execute();
}
