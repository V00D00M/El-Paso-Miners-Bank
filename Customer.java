import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Customer class represents a customer with a unique ID and a list of accounts.
 */
public class Customer extends Person {
    protected String customerID;
    protected List<Account> account;

    /**
     * Constructs a new Customer with the specified details.
     *
     * @param customerID the unique ID of the customer
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param address the address of the customer
     * @param DOB the date of birth of the customer
     * @param phoneNumber the phone number of the customer
     */
    public Customer(String customerID,String firstName, String lastName, String address, String DOB, String phoneNumber) {
        super(firstName, lastName, address, DOB, phoneNumber);
        this.customerID = customerID;
        this.account = new ArrayList<Account>(); // Initialize the account list correctly
    }

    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber the account number to search for
     * @return the Account object if found
     * @throws IllegalArgumentException if the account is not found or the customer has no accounts
     */
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

    /**
     * Gets the unique ID of the customer.
     *
     * @return the customer ID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Adds an account to the customer's account list.
     *
     * @param populateAcc the account to add
     */
    public void popAccList(Account populateAcc){
        this.account.add(populateAcc);
    }
    
    /**
     * Retrieves the logged-in customer by their identification number.
     *
     * @param identificationNumber the identification number to search for
     * @param customerMap the array of customers to search in
     * @return the Customer object if found
     * @throws IllegalArgumentException if the customer is not found
     */
    public Customer getLoggedInUser(String identificationNumber, Map<String, Customer> customerMap) {
        Customer cx = customerMap.get(identificationNumber);
            if (cx.getCustomerID().equals(identificationNumber)) {
                return cx;
            }
            throw new IllegalArgumentException("We do not recognize this account number, please try again");
    }

    /**
     * Retrieves the credit account of the customer.
     * 
     * @return the Credit account if found
     * @throws IllegalArgumentException if the customer has no credit account
     */
    public Credit getCreditMax() {
        for (Account acc : this.account) {
            if (acc instanceof Credit) {
                return (Credit) acc;
            }
        }
        throw new IllegalArgumentException("You do not have a credit account with us");
    }
}
