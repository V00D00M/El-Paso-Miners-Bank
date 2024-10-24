import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    protected String customerID;
    protected List<Account> account;
    

    public Customer(String customerID,String firstName, String lastName, String address, String DOB, String phoneNumber) {
        super(firstName, lastName, address, DOB, phoneNumber);
        this.customerID = customerID;
        this.account = new ArrayList<Account>(); // Initialize the account list correctly 
    }

    public Account getAccount(int accountNumber) {
        if (this.account.size() == 0) {
            throw new IllegalArgumentException("We do not recognize this account number, please try again");
        }
        for (Account acc : this.account) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc; // Return the account if found
            }
        }
        throw new IllegalArgumentException("Account was not found, please try again"); // Throw exception if not found
    }
}