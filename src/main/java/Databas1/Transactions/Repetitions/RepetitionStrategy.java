package Databas1.Transactions.Repetitions;

import Databas1.Transactions.TransactionManager;
import Databas1.Transactions.Transaction;

public interface RepetitionStrategy {
    void repeat(TransactionManager transactionManager, Transaction transaction, int count);
}
