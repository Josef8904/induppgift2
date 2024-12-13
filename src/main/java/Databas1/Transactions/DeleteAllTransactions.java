package Databas1.Transactions;

import Databas1.Commands.Command;

public class DeleteAllTransactions implements Command {
    private TransactionManager transactionManager;


    public DeleteAllTransactions(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void execute() {
        transactionManager.deleteAllTransactions();
        System.out.println("All transactions have been successfully deleted.");
    }
}
