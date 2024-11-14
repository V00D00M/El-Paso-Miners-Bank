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
public void searchPrintLog(Customer customer){
    String fullName = customer.getFirstName() + customer.getLastName();
    if(logs.containsKey(fullName)){
        int i = logs.get(fullName).size();
        for(int j = 0; i > j; j++){
            System.out.println(logs.get(fullName).get(j));
        }
    } else {
        System.out.println("This customer does not have an entry");
    }
}

}