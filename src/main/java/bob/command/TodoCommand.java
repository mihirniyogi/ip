package bob.command;

import java.io.IOException;

import bob.task.TaskList;
import bob.task.Todo;
import bob.util.Formatter;

/**
 * This class represents a command to add a todo task.
 */
public class TodoCommand extends Command {

    private Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public String execute() {
        try {
            TaskList.addTask(this.todo);
            String output = Formatter.format("Bob is on it! I've added this task:",
                    this.todo.toString(),
                    "Now you have " + TaskList.getCount() + " task(s).");
            return output;
        } catch (IOException e) {
            return "Uh oh! Bob says...I couldn't save the task to the file.";
        }
    }
}
