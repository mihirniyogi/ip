package bob.command;

import java.io.IOException;
import java.time.LocalDateTime;

import bob.task.Deadline;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * This class represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {

    private Deadline deadline;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.deadline = new Deadline(description, by);
    }

    @Override
    public void execute(Ui ui) {
        try {
            TaskList.addTask(this.deadline);
            ui.print("Bob is on it! I've added this task:",
                    this.deadline.toString(),
                    "Now you have " + TaskList.getCount() + " task(s).");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }
    }
}
