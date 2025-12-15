import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Task Manager");
        System.out.println("1. Say hello");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        if (choice == 1) {
            System.out.print("What is your name? ");
            String name = scanner.nextLine();
            System.out.println("Hello again, " + name + "!");
        } else if (choice == 2) {
            System.out.println("Goodbye!");
        } else {
            System.out.println("Invalid option");
        }

        scanner.close();
    }
}
