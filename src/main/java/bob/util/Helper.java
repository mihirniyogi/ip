package bob.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class contains static helper methods for Bob.
 */
public class Helper {
    public static String datetimeToReadable(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        return dateTime.format(formatter);
    }

    public static LocalDateTime inputToDateTime(String readable) {
        return LocalDateTime.parse(readable, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
