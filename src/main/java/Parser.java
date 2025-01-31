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

        if (userInput.startsWith("deadline")) {
            String[] command = userInput.split(" ", 2);
            if (command.length == 1 || command[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...the description of a deadline cannot be empty.");
            }
            String[] parts = command[1].split(" /by ");
            if (parts.length == 1 || parts[1].isBlank()) {
                throw new WrongCommandException("Uh oh! Bob says...a deadline task needs a '/by'. e.g. deadline return book /by 18/02/2025 1800");
            }
            String description = parts[0];
            String byInput = parts[1];                    
            LocalDateTime by = Helper.inputToDateTime(byInput);
            return new DeadlineCommand(description, by); 
        }


        throw new WrongCommandException("Unrecognised command!");
    }
}
