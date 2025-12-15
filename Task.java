public class Task {

    String description;
    boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String toString() {
        if (completed) {
            return "[x] " + description;
        } else {
            return "[ ] " + description;
        }
    }
}
