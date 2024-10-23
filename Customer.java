import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    protected String customerID;

    public Customer(String customerID) {
        this.customerID = customerID;
        this.account = new ArrayList<Account>(); // Initialize the account list correctly
    }

    public Account getAccount(int accountNumber) {
        if (this.account.size() == 0) {
            throw new IllegalArgumentException("We do not recognize this account number, please try again");
        }
        for (Account acc : this.account) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc; // Return the account if found
            }
        }
        throw new IllegalArgumentException("We do not recognize this account number, please try again"); // Throw exception if not found
    }
    //Not complete transfer function
    /*public void Transfer(double transferAmt, int accountNumber){
        if(customer.getbalance() < transferAmt){
            System.out.println("Insufficient funds to transfer $" + transferAmt);
        } else {
            customer.getbalance() -= transferAmt;
        }
    }*/
}
