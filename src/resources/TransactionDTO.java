package resources;

public class TransactionDTO {
    private String transactionId;
    private String accountNumber;
    private double amount;
    private String type;
    private String bankName;

    public TransactionDTO(String transactionId, String accountNumber, double amount, String type, String bankName) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.bankName = bankName;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getBankName() {
        return bankName;
    }
}