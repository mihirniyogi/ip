package bob.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.TodoCommand;
import bob.command.UnmarkCommand;
import bob.command.WrongCommandException;
import bob.task.Task;
import bob.task.TaskList;
import bob.util.Helper;

/**
 * This class contains a singular {@code parse()} method to parse and validate user input,
 * throw exceptions if necessary, and return the respective commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the respective command.
     *
     * @param userInput String.
     * @return Command object.
     * @throws WrongCommandException if the user input is invalid.
     * @throws IOException if there is an error saving to file.
     */
    public static Command parse(String userInput) throws WrongCommandException, IOException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        }

        if (userInput.equals("list")) {
            return new ListCommand();
        }

        if (userInput.startsWith("mark")) {
            int number = Integer.parseInt(userInput.split(" ")[1]);
            return new MarkCommand(number);
        }

        if (userInput.startsWith("unmark")) {
            int number = Integer.parseInt(userInput.split(" ")[1]);
            return new UnmarkCommand(number);
        }

        if (userInput.startsWith("find")) {
            String[] parts = userInput.split(" ");

            // if user gives empty search term
            if (parts.length == 1) {
                throw new WrongCommandException("Uh oh! Bob says...the search term cannot be empty.");
            }

            // if user gives more than 1 word as search term
            if (parts.length > 2) {
                throw new WrongCommandException("Uh oh! Bob says...the search term must only be 1 word.");
            }

            String searchTerm = parts[1];
            return new FindCommand(searchTerm);
        }

        if (userInput.startsWith("todo")) {
            String[] parts = userInput.split(" ", 2);

            // if user gives empty description
            if (parts.length == 1 || parts[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...the description of a todo cannot be empty.");
            }

            String description = parts[1];
            return new TodoCommand(description);
        }

        if (userInput.startsWith("deadline")) {
            String[] command = userInput.split(" ", 2);

            // if user gives empty description
            if (command.length == 1 || command[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...the description of a deadline cannot be empty.");
            }

            String[] parts = command[1].split(" /by ");

            // if user gives empty '/by'
            if (parts.length == 1 || parts[1].isBlank()) {
                throw new WrongCommandException("""
                        Uh oh! Bob says...a deadline task needs a '/by'.
                        e.g. deadline return book /by 18/02/2025 1800
                        """);
            }

            String description = parts[0];
            String byInput = parts[1];
            LocalDateTime by = Helper.inputToDateTime(byInput);
            return new DeadlineCommand(description, by);
        }

        if (userInput.startsWith("event")) {
            String[] command = userInput.split(" ", 2);

            // if user gives empty description
            if (command.length == 1 || command[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...the description of an event cannot be empty.");
            }

            String[] parts = command[1].split(" /from ");

            // if user gives empty 'from'
            if (parts.length == 1 || parts[1].isBlank()) {
                throw new WrongCommandException("""
                        Uh oh! Bob says...an event task needs a '/from' and '/to'.
                        e.g. event project meeting /from 17/04/2025 1400 /to 17/04/2025 1600
                        """);
            }

            String description = parts[0];
            String[] dates = parts[1].split(" /to ");

            // if user gives empty 'to'
            if (dates.length == 1 || dates[1].isBlank()) {
                throw new WrongCommandException("""
                        Uh oh! Bob says...an event task needs a '/to'.
                        e.g. event project meeting /from 17/04/2025 1400 /to 17/04/2025 1600
                        """);
            }

            String fromInput = dates[0];
            String toInput = dates[1];

            LocalDateTime from = Helper.inputToDateTime(fromInput);
            LocalDateTime to = Helper.inputToDateTime(toInput);

            return new EventCommand(description, from, to);
        }

        if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");

            // if user gives no task number(s)
            if (parts.length == 1) {
                throw new WrongCommandException("Uh oh! Bob says...the task number(s) to delete cannot be empty.");
            }

            ArrayList<Task> tasks = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                try {
                    int number = Integer.parseInt(parts[i]);
                    tasks.add(TaskList.getTask(number));
                } catch (NumberFormatException e) {
                    throw new WrongCommandException("Uh oh! Bob says...task number(s) must be integers.");
                } catch (IndexOutOfBoundsException e) {
                    throw new WrongCommandException("Uh oh! Bob says...one of the task numbers does not exist.");
                }
            }
            return new DeleteCommand(tasks);
        }

        throw new WrongCommandException("Unrecognised command!");
    }
}
