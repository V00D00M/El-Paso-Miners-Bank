import java.io.BufferedReader;
import java.io.File;
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
    public Map<String, Customer> getCustomers(String fileName) throws IOException {
		Map<String, Customer> customers = new HashMap<>();
        Customer customerTemp;
        Checking checkTemp;
        Savings saveTemp;
        Credit creditTemp;
		String currLine = "";
        String tempAddress = "";
		String [] tokens;

		this.CSVReader =  new Scanner(new File(fileName));
		CSVReader.nextLine(); // Skip the header

		while (CSVReader.hasNextLine()) {
			currLine = CSVReader.nextLine();
			tokens = currLine.split(",");
            //Instantiates customer class 
            tempAddress = tokens[4] + tokens [5] + tokens [6];
			customerTemp = new Customer(tokens[0], tokens[1], tokens[2] , tokens[3], tempAddress, tokens[7]);
            //instantiates check Temp
            checkTemp = new Checking(tokens[8], Double.parseDouble(tokens[9]));
            customerTemp.popAccList(checkTemp);
            //Instatiates saveTemp and populates list
            saveTemp = new Savings(tokens[10], Double.parseDouble(tokens[11]));
            customerTemp.popAccList(saveTemp);
            //assigns customer object to creditTemp and populates list
            creditTemp = new Credit(tokens[12],Integer.parseInt(tokens[13]),Double.parseDouble(tokens[14]));
            customerTemp.popAccList(creditTemp);
            //adds customer to hashmap
			customers.put(customerTemp.getCustomerID(), customerTemp);
		}
		return customers;
	}

    /**
     * Counts the number of lines in the specified file.
     *
     * @param fileName the name of the file to read
     * @return the number of lines in the file
     * @throws IOException if an I/O error occurs
     */
    public int getNumOfLines(String fileName) throws IOException {
		int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }
		return lines;
	}
}