import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// main method of entire project goes here
public class RunBank {
    public static void main (String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);


        CSVReader csvReader = new CSVReader();
        //logged in user is used as a pointer value of type customer, will be fully initialized when user logs in
        Customer loggedInUser = new Customer("","","","","","");
        Customer [] customerDB = csvReader.getCustomers("CS 3331 - Bank Users.csv");

        String identificationNumber = "";
        boolean exit = false;

        System.out.flush();
        System.out.print("Please enter your ID number:");
        identificationNumber = sc.next();

        loggedInUser = loggedInUser.getLoggedInUser(identificationNumber, customerDB);
        System.out.println("Welcome, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        //Start of the program
        while (!exit) {
        // Logs the user in and gets the user's information
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
                            InquireBalance(loggedInUser);
                            break;
                        case 2:
                            System.out.println("Make a Deposit");
                            DepositMenu(loggedInUser);
                            break;
                        case 3:
                            System.out.println("Make a Withdrawal");
                            WithdrawMenu(loggedInUser);
                            break;
                        case 4:
                            System.out.println("Transfer Money Between Accounts");
                            // TransferMenu(loggedInUser);
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
            }
        }
    }

    // Account Menu
    public static void InquireBalance(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        System.out.println("Which account would you like to inquire about?");
        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Credit Account");
        System.out.print("Enter your choice: ");
        String input = sc.next();
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    System.out.println("Checking Account-" + cx.account.get(0).getAccountNumber() + " Balance: $" + cx.account.get(0).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(0).getBalance());
                    log(logOutput);
                    break;
                case 2:
                    System.out.println("Savings Account-" + cx.account.get(1).getAccountNumber() + " Balance: $" + cx.account.get(1).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
                    log(logOutput);
                    break;
                case 3:
                    System.out.println("Credit Account-" + cx.account.get(2).getAccountNumber() + " Balance: $" + cx.account.get(2).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Checking-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(2).getBalance());
                    log(logOutput);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        }
    }


    public static void DepositMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        System.out.println("Which account would you like to make a deposit to?");
        try {
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Credit Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                System.out.print("Enter the amount you would like to deposit: $");
                double amount = sc.nextDouble();
                switch (choice) {
                    case 1:
                        cx.account.get(0).deposit(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " made a deposit to Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(0).getBalance());
                        log(logOutput);
                        break;
                    case 2:
                        cx.account.get(1).deposit(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " made a deposit to Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
                        log(logOutput);
                        break;
                    case 3:
                        cx.account.get(2).deposit(amount);
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " made a deposit to Credit-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(2).getBalance());
                        log(logOutput);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        } 
    }

    public static void WithdrawMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        try {
            System.out.println("Which account would you like to make a withdrawal from?");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Credit Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                System.out.print("Enter the amount you would like to withdraw: $");
                double amount = sc.nextDouble();
                switch (choice) {
                    case 1:
                        cx.account.get(0).withdraw(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(0).getBalance());
                        log(logOutput);
                        break;
                    case 2:
                        cx.account.get(1).withdraw(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
                        log(logOutput);
                        break;
                    case 3:
                        cx.account.get(2).withdraw(amount);
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Credit-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(2).getBalance());
                        log(logOutput);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        }
    }

    public static void verifyPayment(Customer cx, int checkSaveAcc, double amt){
        double accTotal = cx.account.get(checkSaveAcc).getBalance();
        if(amt > accTotal){
            System.out.println("Youre broke and cant pay these bills");
            return;
        } 
        else if((cx.account.get(3).getBalance() + amt) > 0){
            System.out.println("Sorry thats too much money, we'll make the change for you");
            cx.account.get(checkSaveAcc).withdraw(cx.account.get(3).getBalance());
            cx.account.get(3).deposit(cx.account.get(3).getBalance());
        } else {
            System.out.println("Approving account payment");
            System.out.println("Good Job!");

            cx.account.get(checkSaveAcc).withdraw(amt);
            cx.account.get(3).deposit(amt);
        }
    }

    public static void PaymentMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Which account would you like to make a payment from?");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                System.out.print("Enter the amount you would like to pay: $");
                double amount = sc.nextDouble();
                switch (choice) {
                    case 1:
                        verifyPayment(cx, 0, amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        break;
                    case 2:
                        verifyPayment(cx, 1, amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(1).getBalance());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        }
    }

    public static void log(String message) {
        try {
            File file = new File("log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private static void writeBalancesToNewCsv(Customer cx) {
    //     try (FileWriter writer = new FileWriter(getCsvFilePath())) {
    //         for (Account account : cx.account) {
    //             writer.append(account.getAccountNumber())
    //                   .append(',')
    //                   .append(String.valueOf(account.getBalance()))
    //                   .append('\n');
    //         }
    //     } catch (IOException e) {
    //         System.out.println("An error occurred while writing to the CSV file.");
    //         e.printStackTrace();
    //     }
    // }
}

