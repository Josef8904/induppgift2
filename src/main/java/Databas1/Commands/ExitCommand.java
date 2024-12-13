package Databas1.Commands;

import Databas1.Transactions.TransactionManager;

public class ExitCommand implements Command {
    private TransactionManager transactionManager;

    public ExitCommand(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void execute() {
        transactionManager.exitApplication();
    }
}
