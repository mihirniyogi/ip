import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = """
                 ____     _____    ____
                |  _  \\  |     |  |  _  \\
                | |_| /  |  _  |  | |_| /
                |____/   | | | |  |____/      / \\/ \\
                |  _  \\  | |_| |  |  _  \\     \\    /
                | |_| /  |     |  | |_| /      \\  /
                |____/   |_____|  |____/        \\/
                """;

        System.out.println(logo);
        System.out.println("\t" + line + "\n\tHello! I'm Bob ♥" +  "\n\tWhat can I do for you?" + "\n\t" + line);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println("\t" + line + "\n\t" + userInput + "\n\t" + line);
        }
        scanner.close();
        System.out.println("\t" + line + "\n\tThank you and goodbye!\n\t" + line);

    }
}
