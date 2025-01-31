package bob;
public class Todo extends Task {
    public Todo (String description) {
        super(description);
    }

    public Todo(String description, boolean completed) {
        super(description, completed);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCsv() {
        return String.format("T,%s,%b,,,", this.description, this.completed);
    }
}
