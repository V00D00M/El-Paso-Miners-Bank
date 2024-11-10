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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;
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
        //logged in user is used as a pointer value of type customer, will be fully initialized when user logs in
        Customer loggedInUser = new Customer("","","","","","");
        // Customer hashmap that will hold all the customers in the database
        Map<String, Customer> customerDB = csvReader.getCustomers("CS 3331 - Bank Users.csv");

        String identificationNumber = "";
        boolean exit = false;

        System.out.flush();
        askUserRole(customerDB);

        System.out.print("Please enter your ID number:");
        identificationNumber = sc.next();

        // Get the logged in user
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
                            break;
                        case 3: // Make a Withdrawal
                            System.out.println("Make a Withdrawal");
                            WithdrawMenu(loggedInUser);
                            break;
                        case 4: // Transfer Money Between Accounts
                            System.out.println("Transfer Money Between Accounts");
                            TransferMenu(loggedInUser, "", customerDB);
                            break;
                        case 5: // Make a Payment
                            System.out.println("Make a Payment");
                            PaymentMenu(loggedInUser);
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
        writeBalancesToCSV(customerDB);
    }

    public static void CreateAccount(Customer cx, Map<String, Customer> customerDB){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String logOutput = "";
        System.out.println("\nThanks for choosing to create an account with us!");
        System.out.println("Please enter your first name: ");
        String firstName = sc.next();
        System.out.println("Please enter your last name: ");
        String lastName = sc.next();
        System.out.println("Please enter your date of birth: ");
        String DOB = sc.next();
        System.out.println("Please enter your address. Insert it in this format: ");
        System.out.println("Street Address, City, State, Zip Code");
        System.out.println("Address: ");
        String address = sc.next();
        System.out.println("City: ");
        String city = sc.next();
        System.out.println("State: ");
        String state = sc.next();
        System.out.println("Zip code: ");
        String zip = sc.next();
        System.out.println("Phone Number: ");
        String phoneNumber = sc.next();
        System.out.println("Please enter your initial deposit amount: ");
        double initialDeposit = sc.nextDouble();
        System.out.println("Please enter your credit score: ");
        int creditScore = sc.nextInt();

        BankUser newUser = new BankUser(firstName + " " + lastName, DOB, address, city, state, zip, phoneNumber, creditScore);
    
        // Save the new user to the CSV file
        try {
            List<BankUser> users = BankUser.loadUsersFromCSV("CS 3331 - Bank Users.csv");
            users.add(newUser);
            BankUser.saveUsersToCSV(users, "CS 3331 - Bank Users.csv");
        } catch (IOException e) {
            System.out.println("Error saving user to CSV: " + e.getMessage());
        }

        // Log the account creation
        logOutput = ("New account created for " + newUser.getName() + ". " + newUser.getName() + "'s Checking account number is " + newUser.getCheckingAccountNumber() + ", Savings account number is " + newUser.getSavingsAccountNumber() + ", and Credit card account number is " + newUser.getCreditCardAccountNumber());
        log(logOutput);

        System.out.println("Account created successfully!");

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
                    log(logOutput);
                    break;
                case 2: // Savings Account
                    System.out.println("Savings Account-" + cx.account.get(1).getAccountNumber() + " Balance: $" + cx.account.get(1).getBalance());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
                    log(logOutput);
                    break;
                case 3: // Credit Account
                    Credit creditAccount = cx.getCreditMax();
                    System.out.println("Credit Account-" + creditAccount.getAccountNumber() + " Balance: $" + creditAccount.getBalance() + " Credit Max: $" + creditAccount.getCreditMax());
                    logOutput = (cx.firstName + " " + cx.lastName + " made a balance inquiry on Credit-" + creditAccount.getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + creditAccount.getBalance());
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
                    case 1: // Checking Account
                        cx.account.get(0).deposit(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Checking-" + cx.account.get(0).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Checking balance is $" + cx.account.get(0).getBalance());
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).deposit(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Savings balance is $" + cx.account.get(1).getBalance());
                        log(logOutput);
                        break;
                    case 3: // Credit Account
                        cx.account.get(2).deposit(amount);
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " deposited $" + amount + " to Credit-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
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
     * Displays the withdrawal menu and allows the user to make a withdrawal from their account.
     * 
     * @param cx
     */
    public static void WithdrawMenu(Customer cx) {
        Scanner sc = new Scanner(System.in);
        String logOutput = "";
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
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).withdraw(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " withdrew money from Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(1).getBalance());
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
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        verifyPayment(cx, 1, amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        System.out.println("Credit Account Balance: $" + cx.account.get(2).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " Made payment from Savings-" + cx.account.get(1).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s Credit balance is $" + cx.account.get(2).getBalance());
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
                        log(logOutput);
                        break;
                    case 2: // Savings Account
                        cx.account.get(1).withdraw(amount);
                        cx.account.get(0).deposit(amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Savings-" + cx.account.get(1).getAccountNumber() + " to Checking-" + cx.account.get(0).getAccountNumber();
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
                                    log(logOutput);
                                    break;
                                case 2: // Savings Account
                                    cx.account.get(1).withdraw(amount);
                                    account.deposit(amount);
                                    System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                                    logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Savings-" + cx.account.get(1).getAccountNumber() + " to " + recipient.firstName + " " + recipient.lastName + "'s account \n" + account.getAccountNumber();
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
        String logOutput;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("Admin Console");
        Credit creditAccount = new Credit("000000000", 1000, 0);

        while (!exit) {
            System.out.print("\nWhich account would you like to inquire about?\nEnter the name of the account holder: ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                System.out.println("\nThank you for choosing El Paso Miners Bank. Goodbye!\n");
                exit = true;
            } else {
                boolean found = false;
                for (Customer cx : customers.values()) {
                    String fullName = cx.firstName + " " + cx.lastName;
                    if (fullName.equalsIgnoreCase(input)) { // Check if the customer is found
                        found = true;
                        // Display the customer's information
                        System.out.println("\nCustomer Details:");
                        System.out.println("--------------------");
                        System.out.println("ID: " + cx.getCustomerID());
                        System.out.println("Name: " + cx.firstName + " " + cx.lastName);
                        System.out.println("DOB: " + cx.DOB);
                        System.out.println("Address: " + cx.address);
                        System.out.println("Phone Number: " + cx.phoneNumber);
                        System.out.println("Checking Account Number: " + cx.account.get(0).getAccountNumber());
                        System.out.println("Checking Balance: " + cx.account.get(0).getBalance());
                        System.out.println("Savings Account Number: " + cx.account.get(1).getAccountNumber());
                        System.out.println("Savings Balance: " + cx.account.get(1).getBalance());
                        System.out.println("Credit Account Number: " + cx.account.get(2).getAccountNumber());
                        System.out.println("Credit Max: " + creditAccount.getCreditMax());
                        System.out.println("Credit Balance: " + cx.account.get(2).getBalance());
                        logOutput = ("Admin made an account inquiry on " + cx.firstName + " " + cx.lastName + "'s accounts. " + cx.firstName + " " + cx.lastName + "'s Checking balance is $" + cx.account.get(0).getBalance() + ", Savings balance is $" + cx.account.get(1).getBalance() + ", and Credit balance is $" + cx.account.get(2).getBalance());
                        log(logOutput);

                        // Ask the admin if they would like to inquire about another account
                        System.err.println("\nWould you like to inquire about another account? (yes/no)");
                        String choice = sc.nextLine().trim();
                        if (choice.equalsIgnoreCase("no")) {
                            // Ask if they would like to sign in as a customer or exit the program
                            System.err.println("\nWould you like to go back to the main menu or exit the program? (main menu/exit)");
                            String userChoice = sc.nextLine().trim();
                            if (userChoice.equalsIgnoreCase("main menu") || userChoice.equalsIgnoreCase("menu")) {
                                askUserRole(customers);
                                exit = true;
                            } else if (userChoice.equalsIgnoreCase("exit")) {
                                System.out.println("\nThank you for choosing El Paso Miners Bank. Goodbye!\n");
                                exit = true;
                            }
                        }
                        break; // Break out of the loop after handling the found customer
                    }
                }
                if (!found) {
                    System.out.println("Customer not found. Please try again.");
                }
            }
        }
    }

    /**
     * Asks the user for their role and performs actions based on the role.
     *
     * @param customerDB the map of customers in the database
     */
    public static void askUserRole(Map<String, Customer> customers) {
        Scanner sc = new Scanner(System.in);
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
                    adminMenu(customers);
                    break;
                case 2: // Customer
                    // Return to the main method
                    return;
                case 3: // Create a new account

                default:
                    System.out.println("Invalid choice. Please try again.");
                    askUserRole(customers);
                    break;
            }
        } catch (NumberFormatException e) {
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
    private static void writeBalancesToCSV(Map<String, Customer> customers) throws IOException {
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
