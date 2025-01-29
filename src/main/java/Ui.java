import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = """
                 ____     _____    ____
                |  _  \\  |     |  |  _  \\
                | |_| /  |  _  |  | |_| /
                |____/   | | | |  |____/      / \\/ \\
                |  _  \\  | |_| |  |  _  \\     \\    /
                | |_| /  |     |  | |_| /      \\  /
                |____/   |_____|  |____/        \\/
                """;

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void print(String... inputs) {
        System.out.println("\t" + LINE);
        for (String input: inputs) {
            if (input == null) {
            continue;
        }
        System.out.println("\t" + input);
        }
        System.out.println("\t" + LINE);
    }

    public void printLogo() {
        System.out.println(LOGO);
    }

    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            this.print("No tasks yet!");
            return;
        }
        int n = tasks.size();
        String[] taskStrings = new String[n];
        for (int i = 0; i < n; i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }
        this.print("Here are your tasks:", String.join("\n\t", taskStrings));
    }

    public void closeScanner() {
        scanner.close();
    }
}
