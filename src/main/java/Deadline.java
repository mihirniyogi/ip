public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean completed) {
        super(description, completed);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + "by: " + this.by + ")";
    }

    @Override
    public String toCsv() {
        return String.format("D,%s,%b,%s,,", this.description, this.completed, this.by);
    }
}
