package Databas1;

import Databas1.Commands.CommandExecutor;
import java.util.Scanner;

public class Menu {
    private CommandExecutor commandExecutor;
    private Scanner scanner;

    public Menu(CommandExecutor commandExecutor, Scanner scanner) {
        this.commandExecutor = commandExecutor;
        this.scanner = scanner;
    }

    public void display() {
        boolean runProgram = true;

        while (runProgram) {
            System.out.println("\nMain Menu");
            System.out.println("1. Add a transaction");
            System.out.println("2. View your transactions");
            System.out.println("3. Delete all transactions");
            System.out.println("4. Delete a transaction");
            System.out.println("5. View account balance");
            System.out.println("6. Exit the program");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            commandExecutor.executeCommand(choice);
        }

        scanner.close();
    }
}
