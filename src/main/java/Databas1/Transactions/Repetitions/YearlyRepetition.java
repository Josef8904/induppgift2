package Databas1.Transactions.Repetitions;

import Databas1.Transactions.TransactionManager;
import Databas1.Transactions.Transaction;
import java.util.Calendar;

public class YearlyRepetition implements RepetitionStrategy {
    @Override
    public void repeat(TransactionManager transactionManager, Transaction transaction, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transaction.getDate());

        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.YEAR, 1);
            transactionManager.addTransaction(new Transaction(transaction.getDescription() + " (Yearly)", transaction.getAmount(), calendar.getTime()));
        }
    }
}
