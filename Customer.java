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

    public String getCustomerID() {
        return customerID;
    }

    public void popAccList(Account populateAcc){
        this.account.add(populateAcc);
    }
    
    public Customer getLoggedInUser(String identificationNumber, Customer[] customerArr) {
        for (Customer cx : customerArr) {
            if (cx.getCustomerID().equals(identificationNumber)) {
                return cx;
            }
        }
        throw new IllegalArgumentException("We do not recognize this account number, please try again");
    }
}
