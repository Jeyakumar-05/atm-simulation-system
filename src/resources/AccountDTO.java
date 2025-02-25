package resources;

public class AccountDTO {
    private String accountNumber;
    private String accountType;
    private double balance;
    private String bankName;
    private String bankAddress;
    private String contactNo;
    private String username;

    public AccountDTO(String accountNumber, String accountType, double balance, String bankName,
                      String bankAddress, String contactNo, String username) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.contactNo = contactNo;
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getBalance() {
        return balance;
    }
    public String getBankName() {
        return bankName;
    }
    public String getBankAddress() {
        return bankAddress;
    }
    public String getContactNo() {
        return contactNo;
    }
    public String getUsername() {
        return username;
    }
}