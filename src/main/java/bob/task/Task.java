package bob.task;
public abstract class Task {
    protected boolean completed;
    protected final String description;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public abstract String toCsv();

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
