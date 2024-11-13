import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Logger{

    private static Logger instance;

    private Map<String, List<String>> logs = new HashMap<>();

    private Logger() {}

    public static Logger getInstance() {

    if (instance == null) {
        instance = new Logger();
        }
        return instance;
    }

  public void addToLog( Customer customer, String action){
    String firstName = customer.getFirstName();
    String lastName = customer.getLastName();
    String fullName = lastName + "," + firstName;
    if(logs.containsKey(fullName)){
        logs.get(fullName).add(action);
        } else {
            List<String> contLogs = new ArrayList<>();
            contLogs.add(action);
        logs.put(fullName, contLogs);
        }
    }

}