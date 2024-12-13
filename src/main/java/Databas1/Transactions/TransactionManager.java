package Databas1.Transactions;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;

    public TransactionManager() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        System.out.println("Transaction added: " + transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public void deleteAllTransactions() {
        transactions.clear();
        System.out.println("All transactions have been deleted.");
    }

    public double getAccountBalance() {
        double balance = 0;
        for (Transaction transaction : transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public void deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            Transaction removedTransaction = transactions.remove(index);
            System.out.println("Transaction deleted: " + removedTransaction);
        } else {
            System.out.println("Invalid transaction number.");
        }
    }

    public void exitApplication() {
        System.out.println("Exiting the program. Goodbye!");
        System.out.flush();
        System.exit(0);
    }
}
