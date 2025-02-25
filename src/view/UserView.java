package view;

import controller.UserController;
import resources.AccountDTO;
import resources.TransactionDTO;
import java.util.Scanner;

public class UserView {
    private UserController userController = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the User Login!");

        System.out.print("Enter Card ID: ");
        String cardId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();


        AccountDTO account = userController.verifyCardAndGetAccount(cardId, pin);

        if (account != null) {
            System.out.println("Card verified! Bank: " + account.getBankName());
            showMenu(account);
        } else {
            System.out.println("Invalid Card ID or PIN.");
        }
    }

    private void showMenu(AccountDTO account) {
        while (true) {
            System.out.println("\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (userController.withdraw(account, withdrawAmount)) {
                        System.out.println("Withdrawal successful!");

                        TransactionDTO transaction = new TransactionDTO(
                                "TXN" + System.currentTimeMillis(),
                                account.getAccountNumber(),
                                withdrawAmount,
                                "WITHDRAW",
                                account.getBankName()
                        );
                        System.out.println("\nReceipt:\n" + userController.generateReceipt(transaction));
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    userController.deposit(account, depositAmount);
                    System.out.println("Deposit successful!");

                    TransactionDTO transaction = new TransactionDTO(
                            "TXN" + System.currentTimeMillis(),
                            account.getAccountNumber(),
                            depositAmount,
                            "DEPOSIT",
                            account.getBankName()
                    );
                    System.out.println("\nReceipt:\n" + userController.generateReceipt(transaction));
                    break;

                case 3:
                    double balance = userController.checkBalance(account);
                    System.out.println("Your balance is: " + balance);
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
