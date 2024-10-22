import java.util.Scanner;


// main method of entire project goes here
public class RunBank {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        // Start of the program
        while (!exit) {
            System.out.println("Welcome to the El Paso Miners Bank!");
            System.out.println("How can we help you today?\n");
            System.out.println("1. Inquire Account Balance");
            System.out.println("2. Make a Deposit");
            System.out.println("3. Make a Withdrawal");
            System.out.println("4. Transfer Money Between Accounts");
            System.out.println("5. Make a Payment");
            System.out.println("6. Admin Console");
            System.out.println("7. Exit");


            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Inquire Account Balance");
                    break;
                case 2:
                    System.out.println("Make a Deposit");
                    break;
                case 3:
                    System.out.println("Make a Withdrawal");
                    break;
                case 4:
                    System.out.println("Transfer Money Between Accounts");
                    break;
                case 5:
                    System.out.println("Make a Payment");
                    break;
                case 6:
                    System.out.println("Admin Console");
                    break;
                case 7:
                    System.out.println("Thank you for choosing El Paso Miners Bank. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    } 
}
