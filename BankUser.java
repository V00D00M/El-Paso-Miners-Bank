import java.io.*;
import java.util.*;

class BankUser {
    private static int lastUserId;
    private static int lastSavingsAccountNumber = 0;
    private static int lastCheckingAccountNumber = 0;
    private static int lastCreditAccountNumber = 0;

    private int userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private int savingsAccountNumber;
    private int checkingAccountNumber;
    private int creditAccountNumber;
    private int creditLimit;
    private static final Random random = new Random();

    public BankUser() {}

    public BankUser(String firstName, String lastname, String dob, String address, String phoneNumber, int creditLimit) {
        this.userId = ++lastUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.savingsAccountNumber = ++savingsAccountNumber;
        this.checkingAccountNumber = ++checkingAccountNumber;
        this.creditAccountNumber = ++creditAccountNumber;
        this.creditLimit = generateCreditLimit(creditLimit);
    }

    public int getSavingsAccountNumber() {
        return savingsAccountNumber;
    }

    public int getCheckingAccountNumber() {
        return checkingAccountNumber;
    }

    public int getCreditAccountNumber() {
        return creditAccountNumber;
    }

    /**
     * Generates a credit limit based on the user's credit score.
     * @param creditScore
     * @return
     */
    private int generateCreditLimit(int creditScore) {
        if (creditScore <= 580) {
            return 100 + random.nextInt(600);
        } else if (creditScore <= 669) {
            return 700 + random.nextInt(4300);
        } else if (creditScore <= 739) {
            return 5000 + random.nextInt(2499);
        } else {
            return 7500 + random.nextInt(8499);
        }
    }

    public String toCSV(List<BankUser> users, String filePath) throws IOException {
        return String.join(",", String.valueOf(userId), firstName, lastName, dob, address, city, state, zip, phoneNumber, String.valueOf(checkingAccountNumber), String.valueOf(savingsAccountNumber), String.valueOf(creditAccountNumber), String.valueOf(creditLimit));
    }

    public static BankUser fromCustomer(Customer customer) {
        BankUser user = new BankUser(
            customer.getFirstName(),
            customer.getLastName(),
            customer.getDOB(),
            customer.getAddress(),
            customer.getPhoneNumber(),
            0 // Placeholder for credit score, should be set appropriately
        );
        user.userId = Integer.parseInt(customer.getCustomerID());
        for (Account account : customer.getAccounts()) {
            if (account instanceof Checking) {
                user.checkingAccountNumber = Integer.parseInt(account.getAccountNumber());
                lastCheckingAccountNumber = Math.max(lastCheckingAccountNumber, user.checkingAccountNumber);
            } else if (account instanceof Savings) {
                user.savingsAccountNumber = Integer.parseInt(account.getAccountNumber());
                lastSavingsAccountNumber = Math.max(lastSavingsAccountNumber, user.savingsAccountNumber);
            } else if (account instanceof Credit) {
                user.creditAccountNumber = Integer.parseInt(account.getAccountNumber());
                lastCreditAccountNumber = Math.max(lastCreditAccountNumber, user.creditAccountNumber);
            }
        }
        return user;
    }
}
