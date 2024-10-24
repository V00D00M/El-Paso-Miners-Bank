import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    protected String customerID;
    protected List<Account> account;

    public Customer(String customerID,String firstName, String lastName, String address, String DOB, String phoneNumber) {
        this.customerID = customerID;
        super(firstName, lastName, address, DOB, phoneNumber);
        this.account = new ArrayList<Account>(); // Initialize the account list correctly 
    }

    public Account getAccount(String accountNumber) {
        if (this.account.size() == 0) {
            throw new IllegalArgumentException("you dont have an Acc with us");
        }
        for (Account acc : this.account) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc; // Return the account if found
            }
        }
        throw new IllegalArgumentException("We do not recognize this account number, please try again"); // Throw exception if not found
    }
    public void popAccList(Account populateAcc){
        this.account.add(populateAcc);
    }
}
