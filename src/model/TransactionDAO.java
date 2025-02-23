package model;

import resources.TransactionDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {
    public void addTransaction(TransactionDTO transaction) {
        String query = "INSERT INTO transactions (transaction_id, account_number, amount, type, bank_name) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, transaction.getTransactionId());
            stmt.setString(2, transaction.getAccountNumber());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getType());
            stmt.setString(5, transaction.getBankName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}