import java.io.IOException;
import java.time.LocalDateTime;

public class Parser {
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

        if (userInput.startsWith("todo")) {
            String[] parts = userInput.split(" ", 2);
            if (parts.length == 1 || parts[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...the description of a todo cannot be empty.");
            }
            String description = parts[1];            
            return new TodoCommand(description);
        }


        throw new WrongCommandException("Unrecognised command!");
    }
}
