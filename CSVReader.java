import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CSVReader {
    private List<String> lines;
    private String[] userFields;

    public CSVReader(String filePath) throws IOException {
        
        lines = Files.readAllLines(Paths.get(filePath));
    }

    public void findUserByName(String name) {
        for (String line : lines) {
            String[] fields = line.split(",");
            if (fields[1].equals(name)) {
                userFields = fields;
                break;
            }
        }
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
