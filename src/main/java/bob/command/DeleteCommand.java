package bob.command;

import java.io.IOException;

import bob.task.Task;
import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int number;

    public DeleteCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute() {
        try {
            Task task = TaskList.getTask(number);
            TaskList.deleteTask(number);

            String output = Formatter.format("Bob is on it! Deleted this task: ",
                    task.toString(),
                    "Now you have " + TaskList.getCount() + " task(s).");
            return output;
        } catch (IndexOutOfBoundsException e) {
            return "Uh oh! Bob says...the task number does not exist.";
        } catch (IOException e) {
            return "Uh oh! Bob says...I couldn't save the task to the file.";
        }
    }
}
