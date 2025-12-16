import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main {

    static final String FILE_NAME = "tasks.json";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean running = true;

        loadTasks(tasks);

        System.out.println("Welcome to the Task Manager");

        while (running) {
            System.out.println();
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter task description: ");
                String description = scanner.nextLine();

                System.out.print("Enter due date (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                LocalDate dueDate = LocalDate.parse(dateInput);

                tasks.add(new Task(description, dueDate));
                System.out.println("Task added!");

            } else if (choice == 2) {

                if (tasks.isEmpty()) {
                    System.out.println("No tasks yet.");
                } else {
                    System.out.println("Your tasks:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }

            } else if (choice == 3) {

                if (tasks.isEmpty()) {
                    System.out.println("No tasks to complete.");
                } else {
                    System.out.print("Enter task number to complete: ");
                    int taskNumber = scanner.nextInt();
                    scanner.nextLine();

                    if (taskNumber >= 1 && taskNumber <= tasks.size()) {
                        tasks.get(taskNumber - 1).markComplete();
                        System.out.println("Task marked as complete!");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                }

            } else if (choice == 4) {
                saveTasks(tasks);
                System.out.println("Tasks saved. Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid option");
            }
        }

        scanner.close();
    }

static void saveTasks(ArrayList<Task> tasks) {
    try {
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            writer.write(tasks.get(i).toJson());
            if (i < tasks.size() - 1) {
                writer.write(",\n");
            }
        }

        writer.write("\n]");
        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving tasks.");
    }
}


static void loadTasks(ArrayList<Task> tasks) {
    try {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        Scanner fileScanner = new Scanner(file);
        StringBuilder json = new StringBuilder();

        while (fileScanner.hasNextLine()) {
            json.append(fileScanner.nextLine().trim());
        }
        fileScanner.close();

        String content = json.toString();

        // Remove [ and ]
        content = content.substring(1, content.length() - 1);

        if (content.trim().isEmpty()) return;

        String[] items = content.split("\\},\\{");

        for (String item : items) {
            item = item.replace("{", "").replace("}", "");

            String[] fields = item.split(",");

            String description = fields[0].split(":")[1].replace("\"", "").trim();
            boolean completed = Boolean.parseBoolean(fields[1].split(":")[1].trim());
            String dateString = fields[2].split(":")[1].replace("\"", "").trim();

            tasks.add(new Task(description, completed, LocalDate.parse(dateString)));
        }
    } catch (Exception e) {
        System.out.println("Error loading tasks.");
    }


}
}