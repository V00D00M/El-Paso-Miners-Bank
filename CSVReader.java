import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVReader {
    private Scanner CSVReader;

    // Default constructor
    public CSVReader(){

    }

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
}