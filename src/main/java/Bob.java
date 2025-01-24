import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        Helper.printLogo();
        Helper.print("Hello! I'm Bob!", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            
            // exit
            if (userInput.equals("bye")) {
                break;
            
            // list out
            } else if (userInput.equals("list")) {
                printTasks();
                continue;
            
            // mark task
            } else if (userInput.startsWith("mark")) {
                int number = Integer.parseInt(userInput.split(" ")[1]);
                markTask(number);
                Helper.print("Bob is on it! Marked the following as done [X]:", taskList.get(number - 1).toString());
                continue;
            
            // unmark task
            } else if (userInput.startsWith("unmark")) {
                int number = Integer.parseInt(userInput.split(" ")[1]);
                unmarkTask(number);
                Helper.print("Bob is on it! Marked the following as undone [ ]", taskList.get(number - 1).toString());
                continue;
            
            // todo
            } else if (userInput.startsWith("todo")) {
                String description = userInput.split(" ", 2)[1];
                Task task = new Todo(description);
                addTask(task);
                Helper.print("Bob is on it! I've added this task:", task.toString());

            // deadline
            } else if (userInput.startsWith("deadline")) {
                String combined = userInput.split(" ", 2)[1];
                String[] parts = combined.split(" /by ");
                String description = parts[0];
                String by = parts[1];
                Task task = new Deadline(description, by);
                addTask(task);
                Helper.print("Bob is on it! I've added this task:", task.toString());

            // event
            } else if (userInput.startsWith("event")) {

            }
        }
        scanner.close();
        Helper.print("Thank you and goodbye!");

    }


    private static void addTask(Task task) {
        taskList.add(task);
    }

    private static void printTasks() {
        if (taskList.isEmpty()) {
            Helper.print("No tasks yet!");
            return;
        }

        int n = taskList.size();
        String[] taskStrings = new String[n];
        for (int i = 0; i < n; i++) {
            taskStrings[i] = (i + 1) + ". " + taskList.get(i).toString();
        }
        Helper.print("Here are your tasks:", String.join("\n\t", taskStrings));
    }

    private static void markTask(int number) {
        taskList.get(number - 1).mark();
    }

    private static void unmarkTask(int number) {
        taskList.get(number - 1).unmark();
    }

}
