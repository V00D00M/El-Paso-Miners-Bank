import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Logger{

    //singleston instance 
    private static Logger instance;

    //map to store customer names and their actions
    private Map<String, List<String>> logs = new HashMap<>();

    //private constructor to prevent instantiation from outside the class
    private Logger() {}

    //singleton method to get the instance of Logger class
    public static Logger getInstance() {

    if (instance == null) {
        instance = new Logger();
        }
        return instance;
    }

    /*
     * adds an action to the log for a given customer
     * 
     * @param customer the customer whose action needs to be logged
     * @action the action to be logged
     */
public void addToLog(Customer customer, String action) {
    String fullName = formatFullName(customer);
    
    // Add the action to the customer's log
    logs.computeIfAbsent(fullName, k -> new ArrayList<>()).add(action);
}

    /*
     * prints the log for the given customer 
     * 
     * @param customer the customer whose log needs to be printed
     */
public void searchPrintLog(Customer customer){
    String fullName = formatFullName(customer);
    //check if the customer has any logs in the map
    if(logs.containsKey(fullName)){
        int i = logs.get(fullName).size();
        for(int j = 0; i > j; j++){
            System.out.println(logs.get(fullName).get(j));
        }
    } else {
        System.out.println("This customer does not have an entry");
    }
}

/*
 * formats the full name of a customer
 * 
 * @param customer the customer whose full name needs to be formatted
 * @return the formatted full name
 */
private String formatFullName(Customer customer) {
    return customer.getLastName() + ", " + customer.getFirstName();
}

}