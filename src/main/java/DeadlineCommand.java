import java.io.IOException;
import java.time.LocalDateTime;

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
