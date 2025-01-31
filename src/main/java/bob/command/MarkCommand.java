package bob.command;
import java.io.IOException;

import bob.TaskList;
import bob.Ui;

public class MarkCommand extends Command {
    
    private int number;

    public MarkCommand(int number) {
        this.number = number;
    }
    
    @Override
    public void execute(Ui ui) {
        try {
            TaskList.markTask(number);
            ui.print("Bob is on it! Marked the following as done [X]:", 
                    TaskList.getTask(number).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.print("Uh oh! Bob says...the task number does not exist.");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }
    }
}
