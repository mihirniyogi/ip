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
    public String execute() {
        // checks if ALL task numbers entered are valid
        if (!checkValidTasks()) {
            return "Uh oh! Bob says...one of the task numbers does not exist.";
        }

        StringBuilder output = new StringBuilder();
        output.insert(0, "Bob is on it! Deleted the following task(s):");

        try {
            for (Task task : tasks) {
                output.append(task.toString());
                TaskList.deleteTask(task);
            }
            return output.toString();
        } catch (IOException e) {
            return "Uh oh! Bob says...I couldn't save the tasks to the file.";
        }
    }

    private boolean checkValidTasks() {
        for (Task task : tasks) {
            if (!tasks.contains(task)) {
                return false;
            }
        }
        return true;
    }
}
