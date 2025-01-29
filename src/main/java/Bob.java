import java.io.IOException;
import java.util.Scanner;

public class Bob {
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
                    TaskList.printTasks();
                
                // mark task
                } else if (userInput.startsWith("mark")) {
                    int number = Integer.parseInt(userInput.split(" ")[1]);
                    TaskList.markTask(number);
                    Helper.print("Bob is on it! Marked the following as done [X]:", 
                            TaskList.getTask(number).toString());
                
                // unmark task
                } else if (userInput.startsWith("unmark")) {
                    int number = Integer.parseInt(userInput.split(" ")[1]);
                    TaskList.unmarkTask(number);
                    Helper.print("Bob is on it! Marked the following as undone [ ]", 
                            TaskList.getTask(number).toString());
                
                // todo
                } else if (userInput.startsWith("todo")) {
                    String[] parts = userInput.split(" ", 2);
                    if (parts.length == 1 || parts[1].isBlank()) {
                        throw new WrongCommandException("Uh oh! Bob says...the description of a todo cannot be empty.");
                    }
                    String description = parts[1];
                    Task task = new Todo(description);
                    TaskList.addTask(task);
                    Helper.print("Bob is on it! I've added this task:", 
                            task.toString(), 
                            "Now you have " + TaskList.getCount() + " task(s).");
    
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
                    TaskList.addTask(task);
                    Helper.print("Bob is on it! I've added this task:", 
                            task.toString(), 
                            "Now you have " + TaskList.getCount() + " task(s).");
    
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
                    TaskList.addTask(task);
                    Helper.print("Bob is on it! I've added this task:", 
                            task.toString(), 
                            "Now you have " + TaskList.getCount() + " task(s).");
                
                // delete    
                } else if (userInput.startsWith("delete")) {
                    int number = Integer.parseInt(userInput.split(" ")[1]);
                    Task task = TaskList.getTask(number);
                    TaskList.deleteTask(number);
                    Helper.print("Bob is on it! Deleted this task: ", 
                            task.toString(), 
                            "Now you have " + TaskList.getCount() + " task(s).");
                
                // command not recognised
                } else {
                    throw new WrongCommandException("Uh oh! Bob says...I'm sorry, but I don't know what that means :(");
                }
            } catch (WrongCommandException e) {
                Helper.print(e.getMessage(), "Please try again!");
            } catch (IndexOutOfBoundsException e) {
                Helper.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (NumberFormatException e) {
                Helper.print("Uh oh! Bob says...I'm sorry, there is no such task :(");
            } catch (IOException e) {
                Helper.print("Uh oh! Bob says...I'm sorry, there was an error saving the task :(");
            }
        }
        scanner.close();
        Helper.print("Thank you and goodbye!");

    }
}
