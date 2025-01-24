import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        
        String[] tasks = new String[100];
        int currentTask = 0; 

        Helper.printLogo();
        Helper.print("Hello! I'm Bob ♥", "What can I do for you?");
        
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            if (userInput.equals("list")) {
                Helper.print(tasks);
                continue;
            }

            tasks[currentTask] = userInput;
            currentTask++;
            Helper.print("added: " + userInput);
        }
        scanner.close();
        Helper.print("Thank you and goodbye!");

    }
}
