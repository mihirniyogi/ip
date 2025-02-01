package bob.command;

import java.util.List;

import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

public class FindCommand extends Command {
    
    private String searchTerm;

    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public void execute(Ui ui) {
        List<Task> tasks = TaskList.getTasks();
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.toString().contains(this.searchTerm))
                .toList();
        ui.printTasks(filteredTasks);        
    }
}
