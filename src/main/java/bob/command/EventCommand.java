package bob.command;
import java.io.IOException;
import java.time.LocalDateTime;

import bob.Ui;
import bob.task.Event;
import bob.task.TaskList;

public class EventCommand extends Command {
    
    private Event event;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.event = new Event(description, from, to);
    }
    
    @Override
    public void execute(Ui ui) {
        try {
            TaskList.addTask(this.event);
            ui.print("Bob is on it! I've added this task:", 
                    this.event.toString(), 
                    "Now you have " + TaskList.getCount() + " task(s).");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }
    }
}
