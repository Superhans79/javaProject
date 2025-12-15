import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static final String FILE_NAME = "tasks.txt";

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
                tasks.add(new Task(description));
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
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    static void loadTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                boolean completed = Boolean.parseBoolean(parts[0]);
                String description = parts[1];

                tasks.add(new Task(description, completed));
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error loading tasks.");
        }
    }
}
