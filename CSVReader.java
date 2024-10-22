import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {
    private Map<String, String[]> userMapByName = new HashMap<>();
    private Map<String, String[]> userMapBySavingsID = new HashMap<>();
    private Map<String, String[]> userMapByCheckingID = new HashMap<>();
    private String[] userFields;

    public CSVReader(String filePath) throws IOException {
        // Read the CSV file and store the data in a map
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        for (String line : lines) {
            String[] fields = parseCSVLine(line);
            if (fields.length > 1) {
                String key = fields[1] + " " + fields[2];
                userMapByName.put(key, fields);
                userMapBySavingsID.put(fields[0], fields);
                userMapByCheckingID.put(fields[6], fields);

            }
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

    public String[] findUserByName(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        return userMapByName.get(key);
    }

    public String[] findUserByID(String id) {
        return userMapByID.get(id);
    }

    // Getter methods to access individual fields by their index
    public String getField(int index) {
        if (userFields != null && index >= 0 && index < userFields.length) {
            return userFields[index];
        }
        return null;
    }

    // Specific getters for commonly accessed fields
    public String getIdentificationNumber() { return getField(0); }
    public String getFirstName() { return getField(1); }
    public String getLastName() { return getField(2); }
    public String getDateOfBirth() { return getField(3); }
    public String getAddress() { return getField(4); }
    public String getPhoneNumber() { return getField(5); }
    public String getCheckingAccountNumber() { return getField(6); }
    public String getCheckingStartingBalance() { return getField(7); }
    public String getSavingsAccountNumber() { return getField(8); }
    public String getSavingsStartingBalance() { return getField(9); }
    public String getCreditAccountNumber() { return getField(10); }
    public String getCreditMax() { return getField(11); }
    public String getCreditStartingBalance() { return getField(12); }
}
