package bob.command;

import bob.task.TaskList;
import bob.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printTasks(TaskList.getTasks());
    }
}
