import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// main method of entire project goes here
public class RunBank {
    public static void main (String args[]) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        //static String[] userFields;
        CSVReader reader;

        try {
            reader = CSVReader.getInstance("CS 3331 - Bank Users.csv");
            List<String[]> users = reader.getAllUsers();
        } catch (IOException e) {
            System.out.println("Error reading users.csv file.");
            return;
        }

        //Start of the program
        while (!exit) {
            System.out.println("Please enter your name:");


            System.out.println("Welcome to the El Paso Miners Bank!");
            System.out.println("How can we help you today?\n");
            System.out.println("1. Inquire Account Balance");
            System.out.println("2. Make a Deposit");
            System.out.println("3. Make a Withdrawal");
            System.out.println("4. Transfer Money Between Accounts");
            System.out.println("5. Make a Payment");
            System.out.println("6. Admin Console");
            System.out.println("Exit\n");
            System.out.print("Enter your choice: ");

            String input = sc.next();

            // Ask the user for their choice
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("\nThank you for choosing El Paso Miners Bank. Goodbye!\n");
                exit = true;
            } else {
                try {
                    int choice = Integer.parseInt(input);
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
                            //TODO Be sure to give full information of customer when selecting admin console
                        case 6:
                            System.out.println("Admin Console");
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    // Catch the exception if the user enters a non-integer value/choice
                } catch (NumberFormatException e) {
                    System.out.println("\nInvalid choice. Please try again.\n");
                }
                sc.close();
            }
        }

        // public static boolean Login(String name) {
        //     boolean userFields = false;
        //     do {
        //         System.out.println("Please enter your first and last name: ");
        //         String name = sc.next();
        //         userFields = reader.findUserByName(name);
        //         if (userFields == false) {
        //             System.out.println("User not found. Please try again.");
        //         }
        //     }
        //     while (userFields == false);
        //     return userFields;
        // }
    }
}

