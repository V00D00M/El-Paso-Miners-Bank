/* Nathan Ramirez, Alexander Karl, Joel Espino
11/12/24
[CS3331] Project assignment 2
This work was done individually/as a team and completely our own. we did not share, reproduce, or alter any part of this assignment for any purpose.
we did not share code, upload this assignment online in any form, or view/received/modified code written from anyone else. All deliverables were
produced entirely on my/our own. This assignment is part of an academic course at The University of Texas at El Paso and a grade will be assigned
for the work I/we produced.
*/

/* Lab Description:
In this section, Our group was tasked with implementing a system that would be mimicking a bank. 
We had to use our knowledge that we had learned so far in the course from using our design models (such as the waterfall model)
and UML use-case or class diagrams to plan out our implementation of our code. We were tasked with making six Classes that were 
to be dependent and or interact with each other in multiple scenarios such as inquiring about balances, depositing money, withdrawing money, 
transferring money from checking to savings or savings to checking accounts, as well as making payments to the principle for our credit account. 
Additionally we were tasked with adding any classes that we saw were necessary for our application to be able to work, choosing a data structure that 
would best fit with our needs for a database and making logs for each interaction that a customer may have had, be it inquiring about their balance, 
depositing money, transferring money, etc. and having that log be written onto a file that would be appended to. 
Overall we were to be given a description and implement a banking system that would fit the specifications we were given.*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;


/**
 * The RunBank class is the main entry point for the banking system application.
 * It handles user interactions, reads customer data from a CSV file, and performs various banking operations.
 */
public class RunBank {

    /**
     * The main method is the entry point of the application.
     *
     * @param args command-line arguments
     * @throws IOException if an I/O error occurs
     */
    public static void main (String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);


        //CSVReader csvReader = new CSVReader();
        CSVReader csvReader = new CSVReader();
        Map<String, Customer> customerDB = csvReader.getCustomers("CS 3331 - Bank Users.csv");

        String identificationNumber = "";
        boolean exit = false;
        Customer loggedInUser = null;

        System.out.flush();
        askUserRole(customerDB);

        while (true) {
            System.out.print("Please enter your ID number: ");
            identificationNumber = sc.next();

            // Get the logged in user
            loggedInUser = Customer.getLoggedInUser(identificationNumber, customerDB);
            if (loggedInUser == null) {
                System.out.println("Invalid ID number. Please try again.");
            } else {
                System.out.println("\nWelcome, " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
                break;
            }
        }

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
                        case 1: // Inquire Account Balance
                            System.out.println("Inquire Account Balance");
                            InquireBalance(loggedInUser);
                            break;
                        case 2: // Make a Deposit
                            System.out.println("Make a Deposit");
                            DepositMenu(loggedInUser);
                            writeBalancesToCSV(customerDB);
                            break;
                        case 3: // Make a Withdrawal
                            System.out.println("Make a Withdrawal");
                            WithdrawMenu(loggedInUser);
                            writeBalancesToCSV(customerDB);
                            break;
                        case 4: // Transfer Money Between Accounts
                            System.out.println("Transfer Money Between Accounts");
                            TransferMenu(loggedInUser, "", customerDB);
                            writeBalancesToCSV(customerDB);
                            break;
                        case 5: // Make a Payment
                            System.out.println("Make a Payment");
                            PaymentMenu(loggedInUser);
                            writeBalancesToCSV(customerDB);
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

    public static void CreateAccount(Customer cx, Map<String, Customer> customerDB) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String logOutput = "";
        System.out.println("\nThanks for choosing to create an account with us!");
        System.out.println("Please enter your first name: ");
        String firstName = sc.next();
        System.out.println("Please enter your last name: ");
        String lastName = sc.next();
        String fullName = firstName + " " + lastName;

        // Check if the user already exists
        boolean userExists = customerDB.values().stream()
        .anyMatch(customer -> (customer.getFirstName() + " " + customer.getLastName()).equals(fullName));
        // If the user already exists, ask them to try again
        if (userExists) {
            System.out.println("A user with the same name already exists. Please try again.");
            CreateAccount(cx, customerDB);
        }
        
        System.out.println("Please enter your date of birth (D-M-YY): ");
        String DOB = sc.next();
        sc.nextLine();
        System.out.println("Please enter your street address: ");
        System.out.println("Enter your information in this format: street address, city, state(TX) zip code");
        String address = sc.nextLine();
        String addressQuotes = "\"" + address + "\"";
        System.out.println("Please enter your phone number: ");
        String phoneNumber = sc.next();
        System.out.println("Please enter your credit score: ");
        int creditScore = sc.nextInt();
        try {
            List<BankUser> newUserList = new ArrayList<>();
            BankUser.updateLastNumbersFromCSV("CS 3331 - Bank Users.csv", "CS 3331 - Updated Bank Users.csv");
            BankUser newUser = new BankUser(
                BankUser.lastUserId,
                firstName,
                lastName,
                DOB,
                addressQuotes,
                phoneNumber,
                BankUser.lastCheckingAccountNumber,
                BankUser.lastSavingsAccountNumber,
                BankUser.lastCreditAccountNumber,
                creditScore
            );
            newUserList.add(newUser);
    
            // Save the new user to the CSV file
            BankUser bankUserInstance = new BankUser();
            bankUserInstance.toCSV(newUserList, "CS 3331 - Bank Users.csv", "CS 3331 - Updated Bank Users.csv");
            System.out.println("New user created and saved to CSV successfully.");
        
            // Log the account creation
            logOutput = "New account created for " + firstName + " " + lastName + ". Checking account number is " + BankUser.lastCheckingAccountNumber + ", Savings account number is " + BankUser.lastSavingsAccountNumber + ", and Credit account number is " + BankUser.lastCreditAccountNumber;
            log(logOutput);
            System.out.println("Account created successfully!");

        } catch (IOException e) {
            System.out.println("Error saving user to CSV: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
        // Ask the user if they would like to log in
        System.out.println("Would you like to log in to your account? (yes/no)");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("yes")) {
            exit = true;
        }
    }

    /**
     * Displays the inquire balance menu and allows the user to inquire about their account balance.
     * 
     * @param cx
     */
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
                case 1: // Checking Account
                    System.out.println("Checking Account-" + cx.account.get(0).getAccountNumber() + " Balance: $" + cx.account.get(0).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(0).getBalance());
                    Logger.getInstance().addToLog(cx, logOutput);
                    log(logOutput);
                    break;
                case 2: // Savings Account
                    System.out.println("Savings Account-" + cx.account.get(1).getAccountNumber() + " Balance: $" + cx.account.get(1).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
                    Logger.getInstance().addToLog(cx, logOutput);
                    log(logOutput);
                    break;
                case 3: // Credit Account
                    Credit creditAccount = cx.getCreditMax();
                    System.out.println("Credit Account-" + creditAccount.getAccountNumber() + " Balance: $" + creditAccount.getBalance() + " Credit Max: $" + creditAccount.getCreditMax());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Credit-" + creditAccount.getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + creditAccount.getBalance());
                    Logger.getInstance().addToLog(cx, logOutput);
                    log(logOutput);
                    break;
                default: // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        }
    }

    /**
     * Displays the deposit menu and allows the user to make a deposit to their account.
     * 
     * @param cx
     */
    public static void DepositMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
        boolean validChoice = false;
    
        while (!validChoice) {
            System.out.println("Which account would you like to make a deposit to?");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.println("3. Credit Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
    
            try {
                int choice = Integer.parseInt(input);
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
    
                System.out.print("Enter the amount you would like to deposit: $");
                double amount = sc.nextDouble();
    
                switch (choice) {
                    case 1: // Checking Account
                        cx.account.get(0).deposit(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Checking balance is $" + cx.account.get(0).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).deposit(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Savings balance is $" + cx.account.get(1).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    case 3: // Credit Account
                        cx.account.get(2).deposit(amount);
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Credit-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                validChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        }
    }

    /**
     * Displays the withdrawal menu and allows the user to make a withdrawal from their account.
     * 
     * @param cx
     */
    public static void WithdrawMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";

        // Format the balance to two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        
        try {
            System.out.println("Which account would you like to make a withdrawal from?");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
            try {
                int choice = Integer.parseInt(input);
                System.out.print("Enter the amount you would like to withdraw: $");
                double amount = sc.nextDouble();
                switch (choice) {
                    case 1: // Checking Account
                        cx.account.get(0).withdraw(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(0).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).withdraw(amount);
                        System.out.println("Savings Account Balance: $" + df.format(cx.account.get(1).getBalance()));
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + df.format(cx.account.get(1).getBalance()));
                        Logger.getInstance().addToLog(cx, logOutput);
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

    /**
     * Verifies if the user has enough money in their account to make a payment.
     * 
     * @param cx
     * @param checkSaveAcc
     * @param amt
     */
    public static void verifyPayment(Customer cx, int checkSaveAcc, double amt){
        // Check if the user has enough money in their account to make the payment
        double accTotal = cx.account.get(checkSaveAcc).getBalance();
        if(amt > accTotal){
            System.out.println("Youre broke and cant pay these bills");
            return;
        }
        else if(cx.account.size() > 2 && (cx.account.get(2).getBalance() + amt) > 0){
            System.out.println("Sorry thats too much money, we'll make the change for you");
            cx.account.get(checkSaveAcc).withdraw(cx.account.get(3).getBalance());
            cx.account.get(3).deposit(cx.account.get(3).getBalance());
        } else {
            System.out.println("Approving account payment");
            System.out.println("Good Job!");

            cx.account.get(checkSaveAcc).withdraw(amt);
            cx.account.get(2).deposit(amt);
        }
    }

    /**
     * Displays the payment menu and allows the user to make a payment from their account.
     * 
     * @param cx
     */
    public static void PaymentMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput;
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
                    case 1: // Checking Account
                        verifyPayment(cx, 0, amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " Made payment from Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        verifyPayment(cx, 1, amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " Made payment from Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
                        Logger.getInstance().addToLog(cx, logOutput);
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

    /**
     * Displays the transfer menu and allows the user to transfer money between accounts.
     * 
     * @param cx
     * @param accountNumber
     * @param customerDB
     */
    public static void TransferMenu(Customer cx, String accountNumber, Map<String, Customer> customerDB) {
        // Transfer money between accounts
        Scanner sc = new Scanner(System.in);
        String logOutput;
        try {
            System.out.println("Would you like to transfer money to:");
            System.out.println("1. Your own account");
            System.out.println("2. Someone else's account");
            System.out.print("Enter your choice: ");
            String transferChoiceInput = sc.next();
            int transferChoice = Integer.parseInt(transferChoiceInput);
    
            System.out.println("Which account would you like to make a payment from?");
            System.out.println("1. Checking Account");
            System.out.println("2. Savings Account");
            System.out.print("Enter your choice: ");
            String input = sc.next();
            int choice = Integer.parseInt(input);
    
            System.out.print("Enter the amount you would like to pay: $");
            double amount = sc.nextDouble();
            if (transferChoice == 1) {
                // Transfer to own account
                switch (choice) {
                    case 1: // Checking Account
                        cx.account.get(0).withdraw(amount);
                        cx.account.get(1).deposit(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Checking-" + cx.account.get(0).getAccountNumber() + " to Savings-" + cx.account.get(1).getAccountNumber();
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).withdraw(amount);
                        cx.account.get(0).deposit(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Savings-" + cx.account.get(1).getAccountNumber() + " to Checking-" + cx.account.get(0).getAccountNumber();
                        Logger.getInstance().addToLog(cx, logOutput);
                        log(logOutput);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else if (transferChoice == 2) {
                // Transfer to someone else's account
                System.out.print("Enter the recipient's account number: ");
                String recipientAccountNumber = sc.next();
    
                boolean recipientFound = false;
                for (Customer recipient : customerDB.values()) {
                    for (Account account : recipient.account) {
                        //System.out.println("Checking account: " + account.getAccountNumber()); // Debugging statement
                        if (account.getAccountNumber().equals(recipientAccountNumber)) {
                            recipientFound = true;
                            switch (choice) {
                                case 1: // Checking Account
                                    cx.account.get(0).withdraw(amount);
                                    account.deposit(amount);
                                    System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                                    logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Checking-" + cx.account.get(0).getAccountNumber() + " to " + recipient.firstName + " " + recipient.lastName + "'s account \n" + account.getAccountNumber();
                                    Logger.getInstance().addToLog(cx, logOutput);
                                    log(logOutput);
                                    break;
                                case 2: // Savings Account
                                    cx.account.get(1).withdraw(amount);
                                    account.deposit(amount);
                                    System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                                    logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Savings-" + cx.account.get(1).getAccountNumber() + " to " + recipient.firstName + " " + recipient.lastName + "'s account \n" + account.getAccountNumber();
                                    Logger.getInstance().addToLog(cx, logOutput);
                                    log(logOutput);
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                            break;
                        }
                    }
                    if (recipientFound) break;
                }
    
                if (!recipientFound) {
                    System.out.println("Recipient account number not found. Please try again.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nInvalid choice. Please try again.\n");
        }
    }

    /**
     * Displays the admin menu and allows the admin to inquire about a customer's account.
     * 
     * @param customers the map of customers in the database
     */
    public static void adminMenu(Map<String, Customer> customers) {
        TransactionReader transactionReader = new TransactionReader();
        transactionReader.adminMenu(customers);
    }

    /**
     * Asks the user for their role and performs actions based on the role.
     *
     * @param customerDB the map of customers in the database
     */
    public static void askUserRole(Map<String, Customer> customers) {
        Scanner sc = new Scanner(System.in);
        TransactionReader transactionReader = new TransactionReader();
        System.out.println("Are you an Admin or a Customer?");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Create a new account");
        System.out.print("Enter your choice: ");
        String input = sc.next();
        try {
            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("\nThank you for choosing El Paso Miners Bank. Goodbye!\n");
                System.exit(0);
            }
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1: // Admin
                    transactionReader.adminMenu(customers);
                    break;
                case 2: // Customer
                    // Return to the main method
                    return;
                case 3: // Create a new account
                    CreateAccount(customers.get("000000000"), customers);
                    break;
                default:
                    System.out.println("it left the case statement");
                    System.out.println("Invalid choice. Please try again.\n");
                    askUserRole(customers);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("it caught an exception");
            System.out.println("Invalid choice. Please try again.");
            askUserRole(customers);
        }
    }

    /**
     * Logs a message to a file.
     *
     * @param message the message to log
     */
    public static void log(String message) {
        try {
            File file = new File("log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);

            writer.write("[" + formattedDateTime + "] " + message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the updated balances of all customers to a CSV file.
     *
     * @param customers the map of customers to write to the CSV file
     */
    public static void writeBalancesToCSV(Map<String, Customer> customers) throws IOException {
        FileWriter writer = new FileWriter("updated_balances.csv");
        writer.write("Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number,Checking Account Number,Checking Balance,Savings Account Number,Savings Balance,Credit Account Number,Credit Max,Credit Balance\n");
        for (Customer cx : customers.values()) {
            Credit creditAccount = cx.getCreditMax();
            writer.write(cx.getCustomerID() + "," +
                         cx.firstName + "," +
                         cx.lastName + "," +
                         cx.getDOB() + "," +
                         cx.getAddress() + "," +
                         cx.getPhoneNumber() + "," +
                         cx.account.get(0).getAccountNumber() + "," +
                         cx.account.get(0).getBalance() + "," +
                         cx.account.get(1).getAccountNumber() + "," +
                         cx.account.get(1).getBalance() + "," +
                         cx.account.get(2).getAccountNumber() + "," +
                         creditAccount.getCreditMax() + "," +
                         cx.account.get(2).getBalance() + "\n");
        }
        writer.close();
    }
}
