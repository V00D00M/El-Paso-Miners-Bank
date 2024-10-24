import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVReader {
    
    // public List<String[]> allUsers = new ArrayList<>();
    // private String filePath;
    private Scanner CSVReader;

    // Default constructor
    public CSVReader(){

    }
    // ALEXS CODE
    // public CSVReader(String filePath) throws IOException {
    //     this.filePath = filePath;
    //     readCSVFile(filePath);
    // }

    //ALEXS CODE 
    // private void readCSVFile(String filePath) throws IOException {
    //     String line;
    //     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    //         br.readLine(); // Skip header line
    //         while ((line = br.readLine()) != null) {
    //             String[] values = parseCSVLine(line);
    //             allUsers.add(values);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    //This function will create and populate our database from our CSV.
    public Customer[] getCustomers(String fileName) throws IOException {
		Customer[] customers = new Customer[getNumOfLines(fileName)];
        Customer customerTemp;
        Checking checkTemp;
        Savings saveTemp;
        Credit creditTemp;
		String currLine = "";
        String tempAddress = "";
		String [] tokens;
		int index = 0;

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
            //assigns customer object to 
            creditTemp = new Credit(tokens[12],Integer.parseInt(tokens[13]),Double.parseDouble(tokens[14]));
            customerTemp.popAccList(creditTemp);
			customers[index] = customerTemp;
			index++;
		}

		return customers;
	}

    public int getNumOfLines(String fileName) throws IOException {
		this.CSVReader =  new Scanner(new File(fileName));
		int count = 0;

		CSVReader.nextLine();

		while (CSVReader.hasNextLine()) {
			count++;
			CSVReader.nextLine();
		}

		return count;
	}
    

    // Parse a line of CSV data into an array of fields
    // This also handles the addresses that contain commas
    // private String[] parseCSVLine(String line) {
    //     List<String> fields = new ArrayList<>();
    //     StringBuilder currentField = new StringBuilder();
    //     boolean inQuotes = false;

    //     for (char c : line.toCharArray()) {
    //         if (c == '"') {
    //             inQuotes = !inQuotes; // Toggle the inQuotes flag
    //         } else if (c == ',' && !inQuotes) {
    //             fields.add(currentField.toString().trim());
    //             currentField.setLength(0); // Clear the current field
    //         } else {
    //             currentField.append(c);
    //         }
    //     }
    //     fields.add(currentField.toString().trim()); // Add the last field

    //     return fields.toArray(new String[0]);
    // }
    

    //ALEXS CODE 
    // public Customer getLoggedInUser(String identificationNumber, Customer[] customerArr) {
    //     for (Customer cx : customerArr) {
    //         if (cx.getCustomerID().equals(identificationNumber)) {
    //             return cx;
    //         }
    //     }
    //     throw new IllegalArgumentException("We do not recognize this account number, please try again");
    // }

    // public List<String[]> getAllUsers() {
    //     return allUsers;
    // }

    // public static void main(String[] args) {
    //     try {
    //         CSVTestReader reader = new CSVTestReader("CS 3331 - Bank Users.csv");
    //         List<String[]> allUsers = reader.getAllUsers();
    //         for (String[] user : allUsers) {
    //             for (String field : user) {
    //                 System.out.print(field);
    //             }
    //             System.out.println();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}