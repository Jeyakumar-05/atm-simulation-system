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


    public AccountDTO verifyCardAndGetAccount(String cardId, String pin) {

        CardDTO card = cardDAO.verifyCard(cardId, pin);

        if (card != null) {

            return accountDAO.getAccountByNumber(card.getAccountNumber());
        }

        return null;
    }

    public boolean withdraw(AccountDTO account, double amount) {
        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            accountDAO.updateBalance(account.getAccountNumber(), newBalance);


            TransactionDTO transaction = new TransactionDTO(
                    "TXN" + System.currentTimeMillis(),
                    account.getAccountNumber(),
                    amount,
                    "WITHDRAW",
                    account.getBankName()
            );
            transactionDAO.addTransaction(transaction);

            return true;
        }
        return false;
    }

    public void deposit(AccountDTO account, double amount) {
        double newBalance = account.getBalance() + amount;
        accountDAO.updateBalance(account.getAccountNumber(), newBalance);

        TransactionDTO transaction = new TransactionDTO(
                "TXN" + System.currentTimeMillis(),
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
