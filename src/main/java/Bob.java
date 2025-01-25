import java.util.ArrayList;
import java.util.Scanner;

public class Bob {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {

        Helper.printLogo();
        Helper.print("Hello! I'm Bob!", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scanner.nextLine();
            
                // exit
                if (userInput.trim().equals("bye")) {
                    break;
                
                // list out
                } else if (userInput.trim().equals("list")) {
                    printTasks();
                
                // mark task
                } else if (userInput.startsWith("mark")) {
                    int number = Integer.parseInt(userInput.split(" ")[1]);
                    markTask(number);
                    Helper.print("Bob is on it! Marked the following as done [X]:", taskList.get(number - 1).toString());
                
                // unmark task
                } else if (userInput.startsWith("unmark")) {
                    int number = Integer.parseInt(userInput.split(" ")[1]);
                    unmarkTask(number);
                    Helper.print("Bob is on it! Marked the following as undone [ ]", taskList.get(number - 1).toString());
                
                // todo
                } else if (userInput.startsWith("todo")) {
                    String[] parts = userInput.split(" ", 2);
                    if (parts.length == 1 || parts[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...the description of a todo cannot be empty.");
                    }
                    String description = parts[1];
                    Task task = new Todo(description);
                    addTask(task);
                    Helper.print("Bob is on it! I've added this task:", task.toString(), "Now you have " + taskList.size() + " task(s).");
    
                // deadline
                } else if (userInput.startsWith("deadline")) {
                    String[] command = userInput.split(" ", 2);
                    if (command.length == 1 || command[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...the description of a deadline cannot be empty.");
                    }
                    String[] parts = command[1].split(" /by ");
                    if (parts.length == 1 || parts[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...a deadline task needs a '/by'. e.g. deadline return book /by Sunday");
                    }
                    String description = parts[0];
                    String by = parts[1];
                    Task task = new Deadline(description, by);
                    addTask(task);
                    Helper.print("Bob is on it! I've added this task:", task.toString(), "Now you have " + taskList.size() + " task(s).");
    
                // event
                } else if (userInput.startsWith("event")) {
                    String[] command = userInput.split(" ", 2);
                    if (command.length == 1 || command[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...the description of an event cannot be empty.");
                    }
                    String[] parts = command[1].split(" /from ");
                    if (parts.length == 1 || parts[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...an event task needs a '/from' and '/to'. e.g. event project meeting /from Monday 2pm /to Monday 4pm");
                    }
                    String description = parts[0];
                    String[] dates = parts[1].split(" /to ");
                    if (dates.length == 1 || dates[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...an event task needs a '/to'. e.g. event project meeting /from Monday 2pm /to Monday 4pm");
                    }
                    String from = dates[0];
                    String to = dates[1];
                    Task task = new Event(description, from, to);
                    addTask(task);
                    Helper.print("Bob is on it! I've added this task:", task.toString(), "Now you have " + taskList.size() + " task(s).");
                } else {
                    throw new WrongCommandException("Uh oh! Bob says...I'm sorry, but I don't know what that means :(");
                }
            } catch (WrongCommandException e) {
                Helper.print(e.getMessage(), "Please try again!");
            } catch (IndexOutOfBoundsException e) {
                Helper.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (NumberFormatException e) {
                Helper.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
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
