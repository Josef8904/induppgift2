package Databas1.Commands;

import Databas1.Transactions.TransactionManager;

public class ViewBalanceCommand implements Command {
    private TransactionManager transactionManager;

    public ViewBalanceCommand(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void execute() {
        double balance = transactionManager.getAccountBalance();
        System.out.println("Current account balance: " + balance + " SEK");
    }
}
