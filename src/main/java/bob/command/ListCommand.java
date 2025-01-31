package bob.command;

import bob.Ui;
import bob.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printTasks(TaskList.getTasks());
    }
}
