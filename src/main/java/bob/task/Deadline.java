package bob.task;

import java.time.LocalDateTime;
import bob.util.Helper;

/**
 * This class represents a deadline task (inherits from Task).
 * A deadline task has a description and a 'by' datetime, indicating its due date.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by, boolean completed) {
        super(description, completed);
        this.by = by;
    }

    @Override
    public String toString() {
        String byReadable = Helper.datetimeToReadable(this.by);
        return "[D]" + super.toString() + " (" + "by: " + byReadable + ")";
    }

    @Override
    public String toCsv() {
        return String.format("D,%s,%b,%s,,", this.description, this.completed, this.by);
    }
}
