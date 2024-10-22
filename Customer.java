import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Account> accounts;
    

    public Customer(String customerID, String name, String address) {
        super(name, address);
        this.accounts = accounts;
    }
}
