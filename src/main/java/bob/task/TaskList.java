package bob.task;
import java.io.IOException;
import java.util.List;

import bob.storage.Storage;

/**
 * This class represents a list of tasks. It contains static, 
 * methods to manipulate the task list such as:
 * <ul> 
 *     <li> {@link #getCount()} </li>
 *     <li> {@link #getTask(int)} </li>
 *     <li> {@link #getTasks()} </li>
 *     <li> {@link #addTask(Task)} </li>
 *     <li> {@link #deleteTask(int)} </li>
 *     <li> {@link #markTask(int)} </li>
 *     <li> {@link #unmarkTask(int)} </li>
 * </ul>
 */
public class TaskList {
    private static List<Task> tasks;

    static {
        tasks = Storage.fetchTasksFromFile();
    }

    public static int getCount() {
        return tasks.size();
    }

    public static Task getTask(int number) {
        return tasks.get(number - 1);
    }

    public static List<Task> getTasks() {
        return List.copyOf(tasks);
    }

    public static void addTask(Task task) throws IOException{
        tasks.add(task);
        Storage.saveTasksToFile(tasks);
    }

    public static void deleteTask(int number) throws IOException, IndexOutOfBoundsException {
        tasks.remove(number - 1);
        Storage.saveTasksToFile(tasks);
    }

    public static void markTask(int number) throws IOException, IndexOutOfBoundsException {
        tasks.get(number - 1).mark();
        Storage.saveTasksToFile(tasks);
    }

    public static void unmarkTask(int number) throws IOException, IndexOutOfBoundsException {
        tasks.get(number - 1).unmark();
        Storage.saveTasksToFile(tasks);
    }
}
