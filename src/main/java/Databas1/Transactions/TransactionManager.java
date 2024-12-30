package Databas1.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private Connection connection;
    private Integer loggedInUserId;

    public TransactionManager(Connection connection) {
        this.connection = connection;
        this.loggedInUserId = null;
    }

    public boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("User registered successfully!");
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to register user. Username might already exist.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean loginUser(String username, String password) {
        String query = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                loggedInUserId = resultSet.getInt("id");
                System.out.println("Logged in successfully as: " + username);
                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to log in user.");
            e.printStackTrace();
            return false;
        }
    }

    public void logoutUser() {
        if (loggedInUserId != null) {
            System.out.println("User logged out successfully.");
            loggedInUserId = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public boolean isUserLoggedIn() {
        return loggedInUserId != null;
    }

    public void addTransaction(Transaction transaction) {
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in. Please log in to add a transaction.");
            return;
        }

        String query = "INSERT INTO transactions (description, amount, date, user_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, transaction.getDescription());
            statement.setDouble(2, transaction.getAmount());
            statement.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
            statement.setInt(4, loggedInUserId);
            statement.executeUpdate();
            System.out.println("Transaction added to database: " + transaction);
        } catch (SQLException e) {
            System.out.println("Failed to add transaction to database.");
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionsFromDb = new ArrayList<>();
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in.");
            return transactionsFromDb;
        }

        String query = "SELECT * FROM transactions WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loggedInUserId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                java.sql.Date date = resultSet.getDate("date");

                Transaction transaction = new Transaction(description, amount, new java.util.Date(date.getTime()));
                transactionsFromDb.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch transactions from database.");
            e.printStackTrace();
        }
        return transactionsFromDb;
    }

    public void deleteAllTransactions() {
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in. Please log in to delete transactions.");
            return;
        }

        String query = "DELETE FROM transactions WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loggedInUserId);
            int rowsDeleted = statement.executeUpdate();
            System.out.println(rowsDeleted + " transaction(s) deleted from database.");
        } catch (SQLException e) {
            System.out.println("Failed to delete transactions from database.");
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int index) {
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in. Please log in to delete a transaction.");
            return;
        }

        List<Transaction> transactions = getAllTransactions();
        if (index < 0 || index >= transactions.size()) {
            System.out.println("Invalid transaction number. Please try again.");
            return;
        }

        Transaction transactionToDelete = transactions.get(index);
        String query = "DELETE FROM transactions WHERE user_id = ? AND description = ? AND amount = ? AND date = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loggedInUserId);
            statement.setString(2, transactionToDelete.getDescription());
            statement.setDouble(3, transactionToDelete.getAmount());
            statement.setDate(4, new java.sql.Date(transactionToDelete.getDate().getTime()));
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Transaction deleted: " + transactionToDelete);
            } else {
                System.out.println("Transaction could not be deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete transaction from database.");
            e.printStackTrace();
        }
    }


    public double getAccountBalance() {
        if (loggedInUserId == null) {
            System.out.println("No user is currently logged in.");
            return 0;
        }

        double balance = 0;
        String query = "SELECT SUM(amount) AS total_balance FROM transactions WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, loggedInUserId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("total_balance");
            }
        } catch (SQLException e) {
            System.out.println("Failed to calculate account balance.");
            e.printStackTrace();
        }
        return balance;
    }

    public void exitApplication() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close database connection.");
            e.printStackTrace();
        }
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }
}
