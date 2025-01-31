package bob.command;


public class WrongCommandException extends Exception {
    public WrongCommandException(String message) {
        super(message);
    }
}
