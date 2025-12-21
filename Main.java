import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TaskService taskService = new TaskService();
        boolean running = true;

        System.out.println("Welcome to the Task Manager");

        while (running) {
            System.out.println();
            System.out.println("1. Add task");
            System.out.println("2. View tasks (sorted by due date)");
            System.out.println("3. Complete task");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();

                    System.out.print("Enter due date (YYYY-MM-DD): ");
                    LocalDate dueDate = LocalDate.parse(scanner.nextLine());

                    taskService.addTask(new Task(description, dueDate));
                    System.out.println("Task added!");
                    break;

                case 2:
                    if (taskService.getTasks().isEmpty()) {
                        System.out.println("No tasks yet.");
                    } else {
                        for (int i = 0; i < taskService.getTasks().size(); i++) {
                            System.out.println((i + 1) + ". " + taskService.getTasks().get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter task number to complete: ");
                    int number = scanner.nextInt();
                    scanner.nextLine();

                    if (taskService.completeTask(number - 1)) {
                        System.out.println("Task completed!");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;

                case 4:
                    taskService.save();
                    System.out.println("Tasks saved. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }

        scanner.close();
    }
}
