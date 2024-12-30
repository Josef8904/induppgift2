package Databas1;

import Databas1.Commands.*;
import Databas1.Transactions.TransactionManager;
import Databas1.Transactions.AddTransaction;
import Databas1.Transactions.DeleteAllTransactions;
import Databas1.Transactions.DeleteTransaction;
import Databas1.Transactions.ViewTransactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/induppgift2?user=my-databas&password=barcelona";
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return;
        }

        System.out.println("Welcome to Josef's Finance App!");

        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager(conn);
        CommandExecutor commandExecutor = new CommandExecutor();

        while (true) {
            System.out.println("\nMain Menu");
            System.out.println("1. Register a new user");
            System.out.println("2. Log in");
            System.out.println("3. Log out");
            System.out.println("4. Add a transaction");
            System.out.println("5. View your transactions");
            System.out.println("6. Delete all transactions");
            System.out.println("7. Delete a transaction");
            System.out.println("8. View account balance");
            System.out.println("9. Exit the program");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                    transactionManager.registerUser(username, password);
                }
                case 2 -> {
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    transactionManager.loginUser(username, password);
                }
                case 3 -> transactionManager.logoutUser();
                case 4 -> {
                    if (transactionManager.isUserLoggedIn()) {
                        new AddTransaction(transactionManager, scanner).execute();
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 5 -> {
                    if (transactionManager.isUserLoggedIn()) {
                        new ViewTransactions(transactionManager).execute();
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 6 -> {
                    if (transactionManager.isUserLoggedIn()) {
                        new DeleteAllTransactions(transactionManager).execute();
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 7 -> {
                    if (transactionManager.isUserLoggedIn()) {
                        new DeleteTransaction(transactionManager, scanner).execute();
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 8 -> {
                    if (transactionManager.isUserLoggedIn()) {
                        new ViewBalanceCommand(transactionManager).execute();
                    } else {
                        System.out.println("Please log in first.");
                    }
                }
                case 9 -> {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
