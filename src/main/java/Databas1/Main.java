package Databas1;

import Databas1.Commands.*;
import Databas1.Transactions.TransactionManager;
import Databas1.Transactions.AddTransaction;
import Databas1.Transactions.DeleteAllTransactions;
import Databas1.Transactions.DeleteTransaction;
import Databas1.Transactions.ViewTransactions;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Josef's Finance App!");

        Scanner scanner = new Scanner(System.in);
        TransactionManager transactionManager = new TransactionManager();
        CommandExecutor commandExecutor = new CommandExecutor();

        commandExecutor.registerCommand(1, new AddTransaction(transactionManager, scanner));
        commandExecutor.registerCommand(2, new ViewTransactions(transactionManager));
        commandExecutor.registerCommand(3, new DeleteAllTransactions(transactionManager));
        commandExecutor.registerCommand(4, new DeleteTransaction(transactionManager, scanner));
        commandExecutor.registerCommand(5, new ViewBalanceCommand(transactionManager));
        commandExecutor.registerCommand(6, new ExitCommand(transactionManager));

        while (true) {
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

    }
}
