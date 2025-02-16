package bob.command;

import java.io.IOException;
import java.util.ArrayList;

import bob.task.Task;
import bob.task.TaskList;

/**
 * This class represents a command to delete a task (or more).
 */
public class DeleteCommand extends Command {

    private ArrayList<Task> tasks;

    public DeleteCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() throws IOException {

        StringBuilder output = new StringBuilder();
        output.insert(0, "Bob is on it! Deleted the following task(s):");

        for (Task task : tasks) {
            output.append(task.toString());
            TaskList.deleteTask(task);
        }
        return output.toString();
    }
}
