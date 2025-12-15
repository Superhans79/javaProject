public class Task {

    String description;
    boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public void markComplete() {
        this.completed = true;
    }

    public String toFileString() {
        return completed + "|" + description;
    }

    public String toString() {
        if (completed) {
            return "[x] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
