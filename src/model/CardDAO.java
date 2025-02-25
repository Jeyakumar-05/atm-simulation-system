package model;

import resources.CardDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
    public void addCard(CardDTO card) {
        String query = "INSERT INTO cards (card_id, hashed_pin, account_number, bank_name, username, " +
                "card_type, amount_limit, validity, issued_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, card.getCardId());
            stmt.setString(2, SecurityUtil.hash(card.getHashedPin()));
            stmt.setString(3, card.getAccountNumber());
            stmt.setString(4, card.getBankName());
            stmt.setString(5, card.getUsername());
            stmt.setString(6, card.getCardType());
            stmt.setDouble(7, card.getAmountLimit());
            stmt.setDate(8, new java.sql.Date(card.getValidity().getTime()));
            stmt.setDate(9, new java.sql.Date(card.getIssuedDate().getTime()));
            stmt.setString(10, card.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CardDTO verifyCard(String cardId, String pin) {
        String query = "SELECT * FROM cards WHERE card_id = ? AND hashed_pin = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, cardId);
            stmt.setString(2, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CardDTO(
                        rs.getString("card_id"),
                        rs.getString("hashed_pin"),
                        rs.getString("account_number"),
                        rs.getString("bank_name"),
                        rs.getString("username"),
                        rs.getString("card_type"),
                        rs.getDouble("amount_limit"),
                        rs.getDate("validity"),
                        rs.getDate("issued_date"),
                        rs.getString("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
