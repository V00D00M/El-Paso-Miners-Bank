import java.io.*;
import java.util.*;

class BankUser {
    private static int lastUserId = 0;
    private static final Random random = new Random();

    private int userId;
    private String name;
    private String dob;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String savingsAccountNumber;
    private String checkingAccountNumber;
    private String creditCardAccountNumber;
    private int creditLimit;

    public BankUser(String name, String dob, String address, String city, String state, String zip, String phoneNumber, int creditScore) {
        this.userId = ++lastUserId;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.savingsAccountNumber = generateAccountNumber();
        this.checkingAccountNumber = generateAccountNumber();
        this.creditCardAccountNumber = generateAccountNumber();
        this.creditLimit = generateCreditLimit(creditScore);
    }

    public String getSavingsAccountNumber() {
        return savingsAccountNumber;
    }

    public String getCheckingAccountNumber() {
        return checkingAccountNumber;
    }

    public String getCreditCardAccountNumber() {
        return creditCardAccountNumber;
    }

    private String generateAccountNumber() {
        // Generate a random 4-digit number for the account numbers
        return String.format("%04d", random.nextInt(9999));
    }

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

    public String toCSV() {
        return String.join(",", String.valueOf(userId), name, dob, address, city, state, zip, phoneNumber, savingsAccountNumber, checkingAccountNumber, creditCardAccountNumber, String.valueOf(creditLimit));
    }

    public static BankUser fromCSV(String csvLine) {
        String[] fields = csvLine.split(",");
        BankUser user = new BankUser(fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], Integer.parseInt(fields[11]));
        user.userId = Integer.parseInt(fields[0]);
        user.savingsAccountNumber = fields[8];
        user.checkingAccountNumber = fields[9];
        user.creditCardAccountNumber = fields[10];
        user.creditLimit = Integer.parseInt(fields[11]);
        return user;
    }

    public static void saveUsersToCSV(List<BankUser> users, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (BankUser user : users) {
                writer.write(user.toCSV());
                writer.newLine();
            }
        }
    }

    public static List<BankUser> loadUsersFromCSV(String filePath) throws IOException {
        List<BankUser> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(BankUser.fromCSV(line));
            }
        }
        if (!users.isEmpty()) {
            lastUserId = users.get(users.size() - 1).userId;
        }
        return users;
    }
}
