package bob;
import java.io.IOException;

public class TodoCommand extends Command {
    
    private Todo todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }
    
    @Override
    public void execute(Ui ui) {
        try {
            TaskList.addTask(this.todo);
            ui.print("Bob is on it! I've added this task:", 
                    this.todo.toString(), 
                    "Now you have " + TaskList.getCount() + " task(s).");
        } catch (IOException e) {
            ui.print("Uh oh! Bob says...I couldn't save the task to the file.");
        }
    }
}
