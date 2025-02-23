import resources.AccountDTO;
import view.AdminView;
import view.UserView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM System!");
        System.out.println("1. Admin Login\n2. User Login");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                AdminView adminView = new AdminView();
                adminView.start();
                break;

            case 2:
                UserView userView = new UserView();
                userView.start();
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }
}