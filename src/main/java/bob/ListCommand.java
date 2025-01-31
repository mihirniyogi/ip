package bob;
public class ListCommand extends Command {
    @Override
    public void execute(Ui ui) {
        ui.printTasks(TaskList.getTasks());
    }
}
