import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class TransactionReader {

    public void processTransactions(String filePath, Map<String, Customer> customerDB) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] transactionDetails = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                System.out.println("Transaction: " + line);
                String fromFirstName = transactionDetails[0];
                System.out.println(fromFirstName);
                String fromLastName = transactionDetails[1];
                System.out.println(fromLastName);
                String fromWhere = transactionDetails[2];
                System.out.println(fromWhere);
                String action = transactionDetails[3].toLowerCase();
                System.out.println(action);
                String toFirstName = transactionDetails[4];
                System.out.println(toFirstName);
                String toLastName = transactionDetails[5];
                System.out.println(toLastName);
                String toWhere = transactionDetails[6];
                System.out.println(toWhere);
                double amount = Double.parseDouble(transactionDetails[7]);
                System.out.println(amount);

                switch (action) {
                    case "pays":
                        handlePays(fromFirstName, fromLastName, fromWhere, toFirstName, toLastName, toWhere, amount, customerDB);
                        break;
                    case "transfers":
                        handleTransfers(fromFirstName, fromLastName, fromWhere, toFirstName, toLastName, toWhere, amount, customerDB);
                        break;
                    case "inquires":
                        handleInquires(fromFirstName, fromLastName, fromWhere, customerDB);
                        break;
                    case "withdraws":
                        handleWithdraws(fromFirstName, fromLastName, fromWhere, amount, customerDB);
                        break;
                    case "deposits":
                        handleDeposits(toFirstName, toLastName, toWhere, amount, customerDB);
                        break;
                    default:
                        System.out.println("Invalid action: " + action);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Customer findCustomer(String firstName, String lastName, Map<String, Customer> customerDB) {
        for (Customer customer : customerDB.values()) {
            if (customer.getFirstName().equals(firstName) && customer.getLastName().equals(lastName)) {
                return customer;
            }
        }
        return null;
    }
    
    private Account findAccount(Customer customer, String accountType) {
        for (Account account : customer.getAccounts()) {
            System.out.println("Checking account type: " + account.getAccountType() + " against " + accountType);
            if (account.getAccountType().equalsIgnoreCase(accountType.trim())) {
                return account;
            }
        }
        return null;
    }

    private void handlePays(String fromFirstName, String fromLastName, String fromWhere, String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // Get the users's accounts
        Customer fromCustomer = findCustomer(fromFirstName, fromLastName, customerDB);
        Customer toCustomer = findCustomer(toFirstName, toLastName, customerDB);

        if (fromCustomer == null) {
            System.out.println("Payer not found: " + fromFirstName + " " + fromLastName);
            return;
        }

        if (toCustomer == null) {
            System.out.println("Receiver not found: " + toFirstName + " " + toLastName);
            return;
        }


        Account fromAccount = findAccount(fromCustomer, fromWhere);
        Account toAccount = findAccount(toCustomer, toWhere);

        if (fromAccount == null) {
            System.out.println("Payer's account not found: " + fromWhere);
            return;
        }

        if (toAccount == null) {
            System.out.println("Receiver's account not found: " + toWhere);
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds for " + fromFirstName + " " + fromLastName);
            return;
        }

        // Perform the payment
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        System.out.println("Payment of $" + amount + " from " + fromFirstName + " " + fromLastName + " to " + toFirstName + " " + toLastName + " completed successfully.");
    }

    private void handleTransfers(String fromFirstName, String fromLastName, String fromWhere, String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // Get the payer and receiver customers
        Customer fromCustomer = findCustomer(fromFirstName, fromLastName, customerDB);
        Customer toCustomer = findCustomer(toFirstName, toLastName, customerDB);

        if (fromCustomer == null) {
            System.out.println("Payer not found: " + fromFirstName + " " + fromLastName);
            return;
        }

        if (toCustomer == null) {
            System.out.println("Receiver not found: " + toFirstName + " " + toLastName);
            return;
        }

        // Get the payer and receiver accounts
        Account fromAccount = findAccount(fromCustomer, fromWhere);
        Account toAccount = findAccount(toCustomer, toWhere);

        if (fromAccount == null) {
            System.out.println("Payer's account not found: " + fromWhere);
            return;
        }

        if (toAccount == null) {
            System.out.println("Receiver's account not found: " + toWhere);
            return;
        }

        if (fromAccount.getBalance() < amount) {
            System.out.println("Insufficient funds for " + fromFirstName + " " + fromLastName);
            return;
        }

        // Perform the transfer
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        System.out.println("Payment of $" + amount + " from " + fromFirstName + " " + fromLastName + " to " + toFirstName + " " + toLastName + " completed successfully.");
    }

    private void handleInquires(String fromFirstName, String fromLastName, String fromWhere, Map<String, Customer> customerDB) {
        Customer customer = findCustomer(fromFirstName, fromLastName, customerDB);
        if (customer == null) {
            System.out.println("Customer not found: " + fromFirstName + " " + fromLastName);
            return;
        }

        Account account = findAccount(customer, fromWhere);
        if (account == null) {
            System.out.println("Account not found: " + fromWhere);
            return;
        }

        System.out.println("Balance for " + fromFirstName + " " + fromLastName + " in " + fromWhere + ": $" + customer.account.get(0).getBalance());
        System.out.println("Balance for " + fromFirstName + " " + fromLastName + " in " + fromWhere + ": $" + customer.account.get(1).getBalance());
        System.out.println("Balance for " + fromFirstName + " " + fromLastName + " in " + fromWhere + ": $" + customer.account.get(2).getBalance());
    }

    private void handleWithdraws(String fromFirstName, String fromLastName, String fromWhere, double amount, Map<String, Customer> customerDB) {
        // find the customer
        Customer customer = findCustomer(fromFirstName, fromLastName, customerDB);
        if (customer == null) {
            System.out.println("Customer not found: " + fromFirstName + " " + fromLastName);
            return;
        }

        // find the account
        Account account = findAccount(customer, fromWhere);
        if (account == null) {
            System.out.println("Account not found: " + fromWhere);
            return;
        }

        // if the account has insufficient funds, print an error message
        if (account.getBalance() < amount) {
            System.out.println("Insufficient funds for " + fromFirstName + " " + fromLastName);
            return;
        }

        // perform the withdrawal
        account.withdraw(amount);
        System.out.println("Withdrawal of $" + amount + " from " + fromFirstName + " " + fromLastName + " completed successfully.");
    }

    private void handleDeposits(String toFirstName, String toLastName, String toWhere, double amount, Map<String, Customer> customerDB) {
        // find the customer
        Customer customer = findCustomer(toFirstName, toLastName, customerDB);
        if (customer == null) {
            System.out.println("Customer not found: " + toFirstName + " " + toLastName);
            return;
        }

        // find the account
        Account account = findAccount(customer, toWhere);
        if (account == null) {
            System.out.println("Account not found: " + toWhere);
            return;
        }

        // perform the deposit
        account.deposit(amount);
        System.out.println("Deposit of $" + amount + " to " + toFirstName + " " + toLastName + " completed successfully.");
    }

    public void adminMenu(Map<String, Customer> customers) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nAdmin Console");
            System.out.println("1. View all transactions");
            System.out.println("2. Inquire about a specific account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    String filePath = "Transactions.csv";
                    processTransactions(filePath, customers);
                    break;
                case "2":
                    inquireAboutAccount(customers);
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void inquireAboutAccount(Map<String, Customer> customers) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the account holder: ");
        String input = sc.nextLine().trim();

        boolean found = false;
        for (Customer cx : customers.values()) {
            String fullName = cx.getFirstName() + " " + cx.getLastName();
            if (fullName.equalsIgnoreCase(input)) {
                found = true;
                // Display the customer's information
                System.out.println("\nCustomer Details:");
                System.out.println("--------------------");
                System.out.println("ID: " + cx.getCustomerID());
                System.out.println("Name: " + cx.getFirstName() + " " + cx.getLastName());
                System.out.println("DOB: " + cx.getDOB());
                System.out.println("Address: " + cx.getAddress());
                System.out.println("Phone Number: " + cx.getPhoneNumber());
                // Display account details
                for (Account account : cx.getAccounts()) {
                    System.out.println(account.getAccountType() + " Account Number: " + account.getAccountNumber());
                    System.out.println(account.getAccountType() + " Balance: " + account.getBalance());
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Customer not found. Please try again.");
        }
    }

    private void log(String message) {
        // Log the message to a file
        System.out.println("Logging: " + message);
    }
}
