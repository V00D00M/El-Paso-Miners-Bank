import java.util.List;
import java.util.ArrayList;


public class Customer {
    protected String customerID;
    protected List<Account> accounts;

    public Customer(String customerID) {
        this.customerID = customerID;
        this.accounts = new ArrayList<>();
    }

    public void getName() {
        return name;
    }

    public void getAddress() {
        return address;
    }
}
