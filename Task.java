import java.time.LocalDate;

public class Task implements Comparable<Task> {


    String description;
    boolean completed;
    LocalDate dueDate;

    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.completed = false;
        this.dueDate = dueDate;
    }

    public Task(String description, boolean completed, LocalDate dueDate) {
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public void markComplete() {
        this.completed = true;
    }

    // Convert this task into JSON
    public String toJson() {
        return "  {\n" +
               "    \"description\": \"" + description + "\",\n" +
               "    \"completed\": " + completed + ",\n" +
               "    \"dueDate\": \"" + dueDate + "\"\n" +
               "  }";
    }

    public String toString() {
        String status = completed ? "[x]" : "[ ]";
        return status + " " + description + " (Due: " + dueDate + ")";
    }

    @Override
public int compareTo(Task other) {
    return this.dueDate.compareTo(other.dueDate);
}
}
