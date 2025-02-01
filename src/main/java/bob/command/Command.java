package bob.command;

import bob.ui.Ui;

/**
 * This class represents a command. It is an abstract class that
 * contains an abstract method {@code execute(Ui)} that is implemented
 * by its <i>many</i> subclasses.
 */
public abstract class Command {
    public abstract void execute(Ui ui);
}
