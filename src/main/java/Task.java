public class Task {
    private boolean completed;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + description;
    }
}
