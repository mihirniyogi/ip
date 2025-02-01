package bob.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import bob.command.Command;
import bob.command.ExitCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.UnmarkCommand;
import bob.command.WrongCommandException;

public class ParserTest {
    @Test
    public void testParseExit_validInput_returnsExitCommand() throws WrongCommandException, IOException{
        String userInput = "bye";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    public void testParseList_validInput_returnsListcommand() throws WrongCommandException, IOException{
        String userInput = "list";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void testParseMark_validInput_returnsMarkCommand() throws WrongCommandException, IOException{
        String userInput = "mark 1";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof MarkCommand);
    }

    @Test
    public void testParseUnmark_validInput_returnsUnmarkCommand() throws WrongCommandException, IOException{
        String userInput = "unmark 1";
        Command c = Parser.parse(userInput);
        assertTrue(c instanceof UnmarkCommand);
    } 

    @Test
    public void testParseInvalidInput_throwsWrongCommandException() {
        String userInput = "blah blah blah";
        try {
            @SuppressWarnings("unused")
            Command c = Parser.parse(userInput);
        } catch (WrongCommandException e) {
            assertTrue(true);
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}
