package bob.ui;
import java.util.List;
import java.util.Scanner;

import bob.task.Task;

/**
 * This class deals with user interactions, both input and output.
 */
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

    /**
     * Constructs a new Ui object and initialises Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return trimmed version of the user input (String)
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Accepts multiple strings as input, and
     * prints the given inputs line by line.
     * Will print a separation line before and after the inputs.
     *
     * @param inputs String array.
     */
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

    /**
     * Prints the welcome message (Bob's Logo) when Bob starts up.
     */
    public void printWelcome() {
        System.out.println(LOGO);
        this.print("Hello! I'm Bob!", "What can I do for you?");
    }

    /**
     * Prints the list of newline-separated, nicely formatted tasks in the task list.
     *
     * @param tasks List.
     */
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

    /**
     * Closes the scanner object. Called when program exits.
     */
    public void closeScanner() {
        scanner.close();
    }
}
