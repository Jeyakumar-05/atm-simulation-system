package view;

import controller.AdminController;
import resources.AccountDTO;
import resources.CardDTO;
import java.sql.Date;
import java.util.Scanner;

public class AdminView {
    private AdminController adminController = new AdminController();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Admin Login");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (adminController.login(email, password)) {
            System.out.println("Admin login successful!");
            showMenu();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private void showMenu() {
        while (true) {
            System.out.println("\n1. Add Account\n2. Add Card\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addAccount();
                    break;

                case 2:
                    addCard();
                    break;

                case 3:
                    System.out.println("Exiting admin menu.");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addAccount() {
        System.out.println("Add New Account");
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Type (Savings/Current): ");
        String accountType = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter Bank Address: ");
        String bankAddress = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String contactNo = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        AccountDTO account = new AccountDTO(accountNumber, accountType, balance, bankName, bankAddress, contactNo, username);
        adminController.addAccount(account);
        System.out.println("Account added successfully!");
    }

    private void addCard() {
        System.out.println("Add New Card");
        System.out.print("Enter Card ID: ");
        String cardId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Bank Name: ");
        String bankName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Card Type (Platinum/Gold/Silver): ");
        String cardType = scanner.nextLine();
        System.out.print("Enter Amount Limit: ");
        double amountLimit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Validity (YYYY-MM-DD): ");
        String validityStr = scanner.nextLine();
        Date validity = Date.valueOf(validityStr); // Convert String to java.sql.Date
        System.out.print("Enter Issued Date (YYYY-MM-DD): ");
        String issuedDateStr = scanner.nextLine();
        Date issuedDate = Date.valueOf(issuedDateStr); // Convert String to java.sql.Date
        System.out.print("Enter Status (Active/Inactive/Blocked): ");
        String status = scanner.nextLine();

        CardDTO card = new CardDTO(cardId, pin, accountNumber, bankName, username, cardType, amountLimit, validity, issuedDate, status);
        adminController.addCard(card);
        System.out.println("Card added successfully!");
    }
}