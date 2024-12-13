package Databas1.Transactions;

import Databas1.Commands.Command;
import java.util.List;

public class ViewTransactions implements Command {
    private TransactionManager transactionManager;

    public ViewTransactions(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
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
        }
    }
}
