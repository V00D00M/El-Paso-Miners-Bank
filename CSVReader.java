import java.io.File;
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
    public Map<String, Customer> getCustomers(String fileName) throws IOException {
		Map<String, Customer> customers = new HashMap<>();
		String currLine = "";
		String [] tokens;

		this.CSVReader =  new Scanner(new File(fileName));
		CSVReader.nextLine(); // Skip the header

		while (CSVReader.hasNextLine()) {
			currLine = CSVReader.nextLine();
			tokens = currLine.split(",");
            try {
                Customer customer = createCustomer(tokens);
                customers.put(customer.getCustomerID(), customer);
            } catch (Exception e) {
                // Handle exception (e.g., log the error)
                System.err.println("Error processing line: " + currLine);
                e.printStackTrace();
            }
		}
		return customers;
	}

    private Customer createCustomer(String[] tokens) {
        String tempAddress = tokens[4] + tokens[5] + tokens[6];
        Customer customer = new Customer(tokens[0], tokens[1], tokens[2], tokens[3], tempAddress, tokens[7]);

        Checking checkTemp = new Checking(tokens[8], Double.parseDouble(tokens[9]));
        customer.popAccList(checkTemp);

        Savings saveTemp = new Savings(tokens[10], Double.parseDouble(tokens[11]));
        customer.popAccList(saveTemp);

        Credit creditTemp = new Credit(tokens[12], Integer.parseInt(tokens[13]), Double.parseDouble(tokens[14]));
        customer.popAccList(creditTemp);

        return customer;
    }
}