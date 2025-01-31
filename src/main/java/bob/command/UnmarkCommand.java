package bob.command;
import java.io.IOException;

import bob.task.TaskList;
import bob.ui.Ui;

public class UnmarkCommand extends Command {
    
    private int number;

    public UnmarkCommand(int number) {
        this.number = number;
    }
    
    @Override
    public void execute(Ui ui) {
        try {
            TaskList.unmarkTask(number);
            ui.print("Bob is on it! Marked the following as undone [ ]", 
                    TaskList.getTask(number).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.print("Uh oh! Bob says...the task number does not exist.");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }
    }
}
