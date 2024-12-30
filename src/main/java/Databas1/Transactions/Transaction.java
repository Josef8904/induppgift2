package Databas1.Transactions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String description;
    private double amount;
    private Date date;

    public Transaction(String description, double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Description: " + description + ", Amount: " + amount + ", Date: " + sdf.format(date);
    }
}
