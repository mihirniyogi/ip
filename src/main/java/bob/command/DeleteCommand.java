package bob.command;

import java.io.IOException;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * This class represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    @Override
    public void execute(Ui ui) {
        try {
            Task task = TaskList.getTask(number);
            TaskList.deleteTask(number);
            ui.print("Bob is on it! Deleted this task: ", 
                    task.toString(), 
                    "Now you have " + TaskList.getCount() + " task(s).");
        } catch (IndexOutOfBoundsException e) {
            ui.print("Uh oh! Bob says...the task number does not exist.");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }   
    }
}
