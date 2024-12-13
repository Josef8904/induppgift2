package Databas1.Transactions;

import Databas1.Commands.Command;
import java.util.List;
import java.util.Scanner;

public class DeleteTransaction implements Command {
    private TransactionManager transactionManager;
    private Scanner scanner;


    public DeleteTransaction(TransactionManager transactionManager, Scanner scanner) {
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        List<Transaction> transactions = transactionManager.getAllTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("\nHere are your transactions:");
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));
            }

            System.out.print("Enter the number of the transaction you want to delete: ");

            try {
                int index = scanner.nextInt() - 1;
                scanner.nextLine();

                if (index >= 0 && index < transactions.size()) {
                    Transaction removedTransaction = transactions.get(index);
                    transactionManager.deleteTransaction(index);
                    System.out.println("Transaction deleted: " + removedTransaction);
                } else {
                    System.out.println("Invalid choice. Please select a valid transaction number.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }
}
