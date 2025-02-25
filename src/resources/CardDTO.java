package resources;

import java.util.Date;

public class CardDTO {
    private String cardId;
    private String hashedPin;
    private String accountNumber;
    private String bankName;
    private String username;
    private String cardType;
    private double amountLimit;
    private Date validity;
    private Date issuedDate;
    private String status;

    public CardDTO(String cardId, String hashedPin, String accountNumber, String bankName, String username,
                   String cardType, double amountLimit, Date validity, Date issuedDate, String status) {
        this.cardId = cardId;
        this.hashedPin = hashedPin;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.username = username;
        this.cardType = cardType;
        this.amountLimit = amountLimit;
        this.validity = validity;
        this.issuedDate = issuedDate;
        this.status = status;
    }

    public String getCardId() {
        return cardId;
    }
    public String getHashedPin() {
        return hashedPin;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getBankName() {
        return bankName;
    }
    public String getUsername() {
        return username;
    }
    public String getCardType() {
        return cardType;
    }
    public double getAmountLimit() {
        return amountLimit;
    }
    public Date getValidity() {
        return validity;
    }
    public Date getIssuedDate() {
        return issuedDate;
    }
    public String getStatus() {
        return status;
    }
}
