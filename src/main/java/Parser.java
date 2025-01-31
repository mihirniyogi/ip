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

        throw new WrongCommandException("Unrecognised command!");
    }
}
