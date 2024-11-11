import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The CSVReader class provides functionality to read customer data from a CSV file
 * and create Customer objects.
 */
public class CSVReader {
    private Scanner CSVReader;

    /**
     * Default constructor for CSVReader.
     */
    public CSVReader(){}    
    
    /**
    * Reads customer data from a CSV file and returns a map of Customer objects.
    *
    * @param fileName the name of the CSV file to read
    * @return a map of Customer objects with customer ID as the key
    * @throws IOException if an I/O error occurs
    */
    public Map<String, Customer> getCustomers(String filePath) throws IOException {
        Map<String, Customer> customerDB = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String headerLine = reader.readLine();
        String[] headers = headerLine.split(",");

        // Create a mapping of column names to their indices
        Map<String, Integer> columnMapping = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            columnMapping.put(headers[i].trim(), i);
        }

        // Read each line of the CSV file and create a Customer object
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = parseLine(line);
            Customer customer = parseCustomer(fields, columnMapping);
            customerDB.put(customer.getCustomerID(), customer);
        }
        reader.close();
        return customerDB;
    }

    // Checks specifically for entries with " " to avoid the address issue
    private String[] parseLine(String line) {
        // Split the line by commas, but handle quoted fields (e.g., addresses with commas)
        String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (int i = 0; i < fields.length; i++) {
            fields[i] = fields[i].replaceAll("^\"|\"$", "").trim(); // Remove surrounding quotes and trim
        }
        return fields;
    }

    private Customer parseCustomer(String[] fields, Map<String, Integer> columnMapping) {
        String id = fields[columnMapping.get("Identification Number")];
        String firstName = fields[columnMapping.get("First Name")];
        String lastName = fields[columnMapping.get("Last Name")];
        String dob = fields[columnMapping.get("Date of Birth")];
        String address = fields[columnMapping.get("Address")];
        String phoneNumber = fields[columnMapping.get("Phone Number")];

        Customer customer = new Customer(id, firstName, lastName, address, dob, phoneNumber);

        // Create and add accounts to the customer
        String checkingAccountNumber = fields[columnMapping.get("Checking Account Number")];
        double checkingBalance = Double.parseDouble(fields[columnMapping.get("Checking Starting Balance")]);
        Account checkingAccount = new Checking(checkingAccountNumber, checkingBalance);
        customer.addAccount(checkingAccount);

        String savingsAccountNumber = fields[columnMapping.get("Savings Account Number")];
        double savingsBalance = Double.parseDouble(fields[columnMapping.get("Savings Starting Balance")]);
        Account savingsAccount = new Savings(savingsAccountNumber, savingsBalance);
        customer.addAccount(savingsAccount);

        String creditAccountNumber = fields[columnMapping.get("Credit Account Number")];
        int creditMax = Integer.parseInt(fields[columnMapping.get("Credit Max")]);
        double creditBalance = Double.parseDouble(fields[columnMapping.get("Credit Starting Balance")]);
        Account creditAccount = new Credit(creditAccountNumber, creditMax, creditBalance);
        customer.addAccount(creditAccount);

        return customer;
    }
}