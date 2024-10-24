/**
 * The Checking class represents a checking account.
 */
public class Checking extends Account {

    /**
     * Constructs a new Checking account with the specified account number and balance.
     *
     * @param accountNumber the account number of the checking account
     * @param balance the initial balance of the checking account
     */
    public Checking(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Deposits the specified amount into the checking account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;
    }
    

    /**
     * Withdraws the specified amount from the checking account if sufficient funds are available.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (balance > amount) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds to withdraw $" + amount);
        }

    
}    
}
