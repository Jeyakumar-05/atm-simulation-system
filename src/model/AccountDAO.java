package model;

import resources.AccountDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public void addAccount(AccountDTO account) {
        String query = "INSERT INTO accounts (account_number, account_type, balance, bank_name, bank_address, contact_no, username) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.setString(4, account.getBankName());
            stmt.setString(5, account.getBankAddress());
            stmt.setString(6, account.getContactNo());
            stmt.setString(7, account.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountDTO getAccountByNumber(String accountNumber) {
        String query = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new AccountDTO(
                        rs.getString("account_number"),
                        rs.getString("account_type"),
                        rs.getDouble("balance"),
                        rs.getString("bank_name"),
                        rs.getString("bank_address"),
                        rs.getString("contact_no"),
                        rs.getString("username")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Account not found
    }

    public void updateBalance(String accountNumber, double newBalance) {
        String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}