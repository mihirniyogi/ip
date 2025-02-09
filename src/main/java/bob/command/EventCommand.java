package bob.command;

import java.io.IOException;
import java.time.LocalDateTime;

import bob.task.Event;
import bob.task.TaskList;
import bob.util.Formatter;

/**
 * This class represents a command to add an event task.
 */
public class EventCommand extends Command {

    private Event event;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.event = new Event(description, from, to);
    }

    @Override
    public String execute() {
        try {
            TaskList.addTask(this.event);
            String output = Formatter.format("Bob is on it! I've added this task:",
                    this.event.toString(),
                    "Now you have " + TaskList.getCount() + " task(s).");
            return output;
        } catch (IOException e) {
            return "Uh oh! Bob says...I couldn't save the task to the file.";
        }
    }
}
