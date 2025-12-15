import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean running = true;

        System.out.println("Welcome to the Task Manager");

        while (running) {
            System.out.println();
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter task description: ");
                String description = scanner.nextLine();

                Task task = new Task(description);
                tasks.add(task);

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
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid option");
            }
        }

        scanner.close();
    }
}
