import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<String[]> allUsers = new ArrayList<>();
    private String filePath;

    public CSVReader(String filePath) throws IOException {
        this.filePath = filePath;
        readCSVFile(filePath);
    }

    private void readCSVFile(String filePath) throws IOException {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = parseCSVLine(line);
                allUsers.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Parse a line of CSV data into an array of fields
    // This also handles the addresses that contain commas
    private String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes; // Toggle the inQuotes flag
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString().trim());
                currentField.setLength(0); // Clear the current field
            } else {
                currentField.append(c);
            }
        }
        fields.add(currentField.toString().trim()); // Add the last field

        return fields.toArray(new String[0]);
    }

    public String[] getLoggedInUser(String identificationNumber) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = parseCSVLine(line);
                if (userDetails[0].equals(identificationNumber)) {
                    return userDetails;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String[]> getAllUsers() {
        return allUsers;
    }

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