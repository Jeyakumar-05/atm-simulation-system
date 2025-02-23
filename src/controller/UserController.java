package controller;

import model.AccountDAO;
import model.CardDAO;
import model.TransactionDAO;
import resources.AccountDTO;
import resources.CardDTO;
import resources.TransactionDTO;

public class UserController {
    private AccountDAO accountDAO = new AccountDAO();
    private CardDAO cardDAO = new CardDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();

    // Verify card and get the linked account
    public AccountDTO verifyCardAndGetAccount(String cardId, String pin) {
        // Step 1: Verify the card using CardDAO
        CardDTO card = cardDAO.verifyCard(cardId, pin);

        if (card != null) {
            // Step 2: If the card is valid, retrieve the linked account using AccountDAO
            return accountDAO.getAccountByNumber(card.getAccountNumber());
        }

        return null; // Invalid card or PIN
    }

    public boolean withdraw(AccountDTO account, double amount) {
        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            accountDAO.updateBalance(account.getAccountNumber(), newBalance);

            // Log transaction
            TransactionDTO transaction = new TransactionDTO(
                    "TXN" + System.currentTimeMillis(), // Unique transaction ID
                    account.getAccountNumber(),
                    amount,
                    "WITHDRAW",
                    account.getBankName()
            );
            transactionDAO.addTransaction(transaction);

            return true; // Withdrawal successful
        }
        return false; // Insufficient balance
    }

    public void deposit(AccountDTO account, double amount) {
        double newBalance = account.getBalance() + amount;
        accountDAO.updateBalance(account.getAccountNumber(), newBalance);

        // Log transaction
        TransactionDTO transaction = new TransactionDTO(
                "TXN" + System.currentTimeMillis(), // Unique transaction ID
                account.getAccountNumber(),
                amount,
                "DEPOSIT",
                account.getBankName()
        );
        transactionDAO.addTransaction(transaction);
    }

    public double checkBalance(AccountDTO account) {
        return account.getBalance();
    }

    public String generateReceipt(TransactionDTO transaction) {
        return "Transaction ID: " + transaction.getTransactionId() + "\n" +
                "Account Number: " + transaction.getAccountNumber() + "\n" +
                "Amount: " + transaction.getAmount() + "\n" +
                "Type: " + transaction.getType() + "\n" +
                "Bank: " + transaction.getBankName();
    }
}