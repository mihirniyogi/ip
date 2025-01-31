package bob.command;

import bob.TaskList;
import bob.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printTasks(TaskList.getTasks());
    }
}
