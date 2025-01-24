import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Helper.printLogo();
        Helper.print("Hello! I'm Bob ♥", "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            Helper.print(userInput);
        }
        scanner.close();
        Helper.print("Thank you and goodbye!");

    }
}
