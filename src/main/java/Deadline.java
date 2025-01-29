import java.time.LocalDateTime;

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
        String byReadable = Helper.datetimeToReadable(this.by);
        return String.format("D,%s,%b,%s,,", this.description, this.completed, byReadable);
    }
}
