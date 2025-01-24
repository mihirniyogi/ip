import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task getTask(int number) {
        Task task = this.tasks.get(number - 1);
        return task;
    }

    public void addTask(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
    }

    public void markTask(int number) {
        Task task = this.getTask(number);
        task.mark();
    }

    public void unmarkTask(int number) {
        Task task = this.getTask(number);
        task.unmark();
    }

    public void print() {
        String[] taskStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskStrings[i] = (i + 1) + ". " + this.tasks.get(i).toString();
        }
        Helper.print(taskStrings);
    }
}
