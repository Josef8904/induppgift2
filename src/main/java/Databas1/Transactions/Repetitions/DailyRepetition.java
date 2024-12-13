package Databas1.Transactions.Repetitions;

import Databas1.Transactions.TransactionManager;
import Databas1.Transactions.Transaction;
import java.util.Calendar;

public class DailyRepetition implements RepetitionStrategy {
    @Override
    public void repeat(TransactionManager transactionManager, Transaction transaction, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transaction.getDate());

        for (int i = 0; i < count; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            transactionManager.addTransaction(new Transaction(transaction.getDescription() + " (Daily)", transaction.getAmount(), calendar.getTime()));
        }
    }
}
