package Databas1.Transactions;

import Databas1.Commands.Command;
import Databas1.Transactions.Repetitions.RepetitionStrategy;
import Databas1.Transactions.Repetitions.YearlyRepetition;
import Databas1.Transactions.Repetitions.MonthlyRepetition;
import Databas1.Transactions.Repetitions.WeeklyRepetition;
import Databas1.Transactions.Repetitions.DailyRepetition;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTransaction implements Command {
    private TransactionManager transactionManager;
    private Scanner scanner;

    public AddTransaction(TransactionManager transactionManager, Scanner scanner) {
        this.transactionManager = transactionManager;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {

            System.out.print("Enter a description for the transaction: ");
            String description = scanner.nextLine();

            System.out.print("Enter the amount (positive or negative): ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter the date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateInput);

            Transaction transaction = new Transaction(description, amount, date);
            transactionManager.addTransaction(transaction);

            System.out.println("Do you want to repeat this transaction?");
            System.out.println("1. Yearly");
            System.out.println("2. Monthly");
            System.out.println("3. Weekly");
            System.out.println("4. Daily");
            System.out.println("5. No repetition");
            System.out.print("Enter your choice (1-5): ");
            int repeatChoice = scanner.nextInt();
            scanner.nextLine();

            if (repeatChoice >= 1 && repeatChoice <= 4) {
                System.out.print("How many times would you like to repeat it? ");
                int repetitions = scanner.nextInt();
                scanner.nextLine();

                RepetitionStrategy strategy = switch (repeatChoice) {
                    case 1 -> new YearlyRepetition();
                    case 2 -> new MonthlyRepetition();
                    case 3 -> new WeeklyRepetition();
                    case 4 -> new DailyRepetition();
                    default -> null;
                };


                if (strategy != null) {
                    strategy.repeat(transactionManager, transaction, repetitions);
                }
            } else {
                System.out.println("No repetition selected.");
            }


            System.out.println("Transaction successfully added: " + transaction);
        } catch (Exception e) {
            System.out.println("Failed to add transaction. Please try again.");
            e.printStackTrace();
        }
    }
}
