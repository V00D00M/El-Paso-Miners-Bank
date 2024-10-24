/* Nathan Ramirez, Alexander Karl, Joel Espino
10/14/24
[CS3331] Project assignment 1
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
import java.util.ArrayList;
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
        askUserRole(customerDB);

        

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
                            TransferMenu(loggedInUser, "", customerDB);
                            break;
                        case 5:
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
                    case 1:
                        verifyPayment(cx, 0, amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " Made payment from Credit-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(2).getBalance());
                        log(logOutput);
                        break;
                    case 2:
                        verifyPayment(cx, 1, amount);
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = (cx.firstName + " " + cx.lastName + " Made payment from Savings-" + cx.account.get(2).getAccountNumber() + ". " + cx.firstName + " " + cx.lastName + "'s balance is $" + cx.account.get(2).getBalance());
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

    public static void TransferMenu(Customer cx, String accountNumber, Customer[] customerDB) {
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
                    case 1:
                        cx.account.get(0).withdraw(amount);
                        cx.account.get(1).deposit(amount);
                        System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                        System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                        logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Checking-" + cx.account.get(0).getAccountNumber() + " to Savings-" + cx.account.get(1).getAccountNumber();
                        log(logOutput);
                        break;
                    case 2:
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
                for (Customer recipient : customerDB) {
                    for (Account account : recipient.account) {
                        //System.out.println("Checking account: " + account.getAccountNumber()); // Debugging statement
                        if (account.getAccountNumber().equals(recipientAccountNumber)) {
                            recipientFound = true;
                            switch (choice) {
                                case 1:
                                    cx.account.get(0).withdraw(amount);
                                    account.deposit(amount);
                                    System.out.println("Checking Account Balance: $" + cx.account.get(0).getBalance());
                                    logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Checking-" + cx.account.get(0).getAccountNumber() + " to " + recipient.firstName + " " + recipient.lastName + "'s account " + account.getAccountNumber();
                                    log(logOutput);
                                    break;
                                case 2:
                                    cx.account.get(1).withdraw(amount);
                                    account.deposit(amount);
                                    System.out.println("Savings Account Balance: $" + cx.account.get(1).getBalance());
                                    logOutput = cx.firstName + " " + cx.lastName + " transferred $" + amount + " from Savings-" + cx.account.get(1).getAccountNumber() + " to " + recipient.firstName + " " + recipient.lastName + "'s account " + account.getAccountNumber();
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

    public static void adminMenu(Customer[] customers) {
        String logOutput;
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        System.out.println("Admin Console");
    
        while (!found) {
            System.out.print("Which account would you like to inquire about? Enter the name of the account holder: ");
            String input = sc.nextLine().trim();
    
            try {
                for (Customer cx : customers) {
                    String fullName = cx.firstName + " " + cx.lastName;
                    if (fullName.equalsIgnoreCase(input)) {
                        found = true;
                        System.out.println("Customer Details:");
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
                        System.out.println("Credit Max: " + cx.account.get(2).getCreditMax());
                        System.out.println("Credit Balance: " + cx.account.get(2).getBalance());
                        logOutput = ("Admin made an account inquiry on " + cx.firstName + " " + cx.lastName + "'s accounts. " + cx.firstName + " " + cx.lastName + "'s Checking balance is $" + cx.account.get(0).getBalance() + ", Savings balance is $" + cx.account.get(1).getBalance() + ", and Credit balance is $" + cx.account.get(2).getBalance());
                        log(logOutput);
                        System.exit(0);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
    
            if (!found) {
                System.out.println("Customer not found. Please try again.");
            }
        }
    }

    public static void askUserRole(Customer[] customers) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you an Admin or a Customer?");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.print("Enter your choice: ");
        String input = sc.next();
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    adminMenu(customers);
                    break;
                case 2:
                    break;
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

    private static void writeBalancesToCSV(Customer[] customers) {
        try (FileWriter writer = new FileWriter("updated_balances.csv")) {
            writer.append("Identification Number,First Name,Last Name,Date of Birth,Address,Phone Number,Checking Account Number,Checking Balance,Savings Account Number,Savings Balance,Credit Account Number,Credit Max,Credit Balance\n");
            for (Customer cx : customers) {
                writer.append(cx.getCustomerID()).append(',')
                .append(cx.firstName).append(',')
                .append(cx.lastName).append(',')
                .append(cx.getDOB()).append(',')
                .append(cx.getAddress()).append(',')
                .append(cx.getPhoneNumber()).append(',')
                .append(cx.account.get(0).getAccountNumber()).append(',')
                .append(String.valueOf(cx.account.get(0).getBalance())).append(',')
                .append(cx.account.get(1).getAccountNumber()).append(',')
                .append(String.valueOf(cx.account.get(1).getBalance())).append(',')
                .append(cx.account.get(2).getAccountNumber()).append(',')
                .append(String.valueOf(cx.account.get(2).getCreditMax())).append(',')
                .append(String.valueOf(cx.account.get(2).getBalance())).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

