import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CSVTestReader {
    private Scanner fileReader;

public Customer[] getCustomer(String fileName) throws IOException {
Customer[] customers = new Customer[getNumOfLines(fileName)];
Customer customer;
String currLine = "";
String[] tokens;
int index = 0;

this.fileReader =  new Scanner(new File(fileName));
fileReader.nextLine(); // Skip the header

while (fileReader.hasNextLine()) {
currLine = fileReader.nextLine();
tokens = currLine.split(",");

flight = new Flight(tokens[0], tokens[1], Double.parseDouble(tokens[2]) , Double.parseDouble(tokens[3]));
flights[index] = flight;
index++;
}

return flights;
}

public int getNumOfLines(String fileName) throws IOException {
this.fileReader =  new Scanner(new File(fileName));
int count = 0;

fileReader.nextLine();

while (fileReader.hasNextLine()) {
count++;
fileReader.nextLine();
}

return count;
}
}
