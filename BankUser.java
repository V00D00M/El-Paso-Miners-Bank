import java.io.*;
import java.util.*;

class BankUser {
    public static int lastCheckingAccountNumber = 0;
    public static int lastCreditAccountNumber = 0;
    public static int lastUserId;
    public static int lastSavingsAccountNumber = 0;

    private int userID;
    private String password;
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

    /**
     * Default constructor for BankUser.
     * Initializes the user with default values.
     * 
     * @return BankUser
     * @param none
     * @throws none
     */
    public BankUser() {}

    public BankUser(int userID, String password, String firstName, String lastName, String dob,
                    String address, String phoneNumber, int checkingAccountNumber,
                    int savingsAccountNumber, int creditAccountNumber, int creditLimit) {
        this.userID = userID;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.savingsAccountNumber = checkingAccountNumber;
        this.checkingAccountNumber = savingsAccountNumber;
        this.creditAccountNumber = creditAccountNumber;
        this.creditLimit = generateCreditLimit(creditLimit);
    }

    /**
     * Constructor for BankUser.
     * Initializes the user with the given values.
     * 
     * @return BankUser
     * @param userID
     * @param password
     * @param firstName
     * @param lastName
     * @param dob
     * @param address
     * @param phoneNumber
     * @param creditScore
     * @param checkingAccountNumber
     * @param savingsAccountNumber
     * @param creditAccountNumber
     * @throws none
     */
    public int getUserId() {
        return lastUserId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDOB() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSavingsAccountNumber() {
        return lastSavingsAccountNumber;
    }

    public int getCheckingAccountNumber() {
        return lastCheckingAccountNumber;
    }

    public int getCreditAccountNumber() {
        return lastCreditAccountNumber;
    }

    public int getCreditLimit() {
        return creditLimit;
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

    /**
     * Writes the updated bank users to a new CSV file.
     * @param users
     * @param originalFilePath
     * @param newFilePath
     * @throws IOException
     */
    public void toCSV(List<BankUser> users, String originalFilePath, String newFilePath) throws IOException {
        File originalFile = new File(originalFilePath);
        File newFile = new File(newFilePath);

        // Ensure the new file exists
        if (!newFile.exists()) {
            newFile.createNewFile();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(originalFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newFile, true))) {
            String line;

            // If the new file is empty, write the header line
            if (newFile.length() == 0 && (line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // Copy existing users to the new CSV file
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }

            // Append the new users to the new CSV file
            for (BankUser user : users) {
                writer.write(String.join(",", 
                    String.valueOf(user.getUserId()), 
                    user.getPassword(),
                    user.getFirstName(), 
                    user.getLastName(), 
                    user.getDOB(), 
                    user.getAddress(), 
                    user.getPhoneNumber(), 
                    String.valueOf(user.getCheckingAccountNumber()),
                    String.valueOf(100),
                    String.valueOf(user.getSavingsAccountNumber()),
                    String.valueOf(100),
                    String.valueOf(user.getCreditAccountNumber()), 
                    String.valueOf(user.getCreditLimit()),
                    String.valueOf(-1000.00)
                ));
                writer.newLine();
            }
        }
    }

    /**
     * Converts a Customer object to a BankUser object.
     * @param customer
     * @return
     */
    public static BankUser fromCustomer(Customer customer) {
        int id = Integer.parseInt(customer.getCustomerID()); // Assuming there's a method to get the ID
        String password = customer.getPassword();
        String firstName = customer.getFirstName();
        String lastName = customer.getLastName();
        String dob = customer.getDOB();
        String address = customer.getAddress();
        String phoneNumber = customer.getPhoneNumber();
        int creditScore = customer.getCreditScore(); // Assuming there's a method to get the credit score

        // Initialize account numbers
        int checkingAccountNumber = 0;
        int savingsAccountNumber = 0;
        int creditAccountNumber = 0;

        // Iterate through the customer's accounts and set the account numbers
        for (Account account : customer.getAccounts()) {
            if (account instanceof Checking) {
                checkingAccountNumber = Integer.parseInt(account.getAccountNumber());
            } else if (account instanceof Savings) {
                savingsAccountNumber = Integer.parseInt(account.getAccountNumber());
            } else if (account instanceof Credit) {
                creditAccountNumber = Integer.parseInt(account.getAccountNumber());
            }
        }

        // Create the BankUser object
        BankUser user = new BankUser(
            id,
            password,
            firstName,
            lastName,
            dob,
            address,
            phoneNumber,
            creditScore,
            checkingAccountNumber,
            savingsAccountNumber,
            creditAccountNumber
        );

        return user;
    }

    /**
     * Updates the last numbers based on the CSV file.
     * @param originalFilePath
     * @param newFilePath
     * @throws IOException
     */
    public static void updateLastNumbersFromCSV(String originalFilePath, String newFilePath) throws IOException {
        File file = new File(newFilePath);
        String filePathToRead = file.exists() ? newFilePath : originalFilePath;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePathToRead));
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFilePath, true))) {
            boolean firstLine = true;
            String line = reader.readLine(); // Read the header line
            if (line == null) {
                throw new IOException("CSV file is empty");
            }

            String[] headers = line.split(",");
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                int userId = Integer.parseInt(fields[headerMap.get("Identification Number")]);
                int checkingAccountNumber = Integer.parseInt(fields[headerMap.get("Checking Account Number")]);
                int savingsAccountNumber = Integer.parseInt(fields[headerMap.get("Savings Account Number")]);
                int creditAccountNumber = Integer.parseInt(fields[headerMap.get("Credit Account Number")]);

                if (userId > lastUserId) {
                    lastUserId = userId;
                }
                if (checkingAccountNumber > lastCheckingAccountNumber) {
                    lastCheckingAccountNumber = checkingAccountNumber;
                }
                if (savingsAccountNumber > lastSavingsAccountNumber) {
                    lastSavingsAccountNumber = savingsAccountNumber;
                }
                if (creditAccountNumber > lastCreditAccountNumber) {
                    lastCreditAccountNumber = creditAccountNumber;
                }
            }
        }

        // Increment the last numbers for the new user
        lastUserId++;
        lastCheckingAccountNumber++;
        lastSavingsAccountNumber++;
        lastCreditAccountNumber++;
    }
}
