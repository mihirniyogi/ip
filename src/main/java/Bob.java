import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        
        TaskList tasks = new TaskList();

        Helper.printLogo();
        Helper.print("Hello! I'm Bob ♥", "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine();
            
            // exit
            if (userInput.equals("bye")) {
                break;
            
            // list out
            } else if (userInput.equals("list")) {
                tasks.print();
                continue;
            
            // mark task
            } else if (userInput.startsWith("mark")) {
                int number = Integer.parseInt(userInput.split(" ")[1]);
                tasks.markTask(number);
                String taskString = tasks.getTask(number).toString();
                Helper.print("Bob is on it! Marked the following as done:", taskString);
                continue;
            
            // unmark task
            } else if (userInput.startsWith("unmark")) {
                int number = Integer.parseInt(userInput.split(" ")[1]);
                tasks.unmarkTask(number);
                String taskString = tasks.getTask(number).toString();
                Helper.print("Bob is on it! Marked the following as undone:", taskString);
                continue;
            }
            
            // Add task
            tasks.addTask(userInput);
            Helper.print("added: " + userInput);
        }
        scanner.close();
        Helper.print("Thank you and goodbye!");

    }
}
