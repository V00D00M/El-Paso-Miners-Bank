/**
 * The Credit class represents a credit account with a maximum credit limit.
 */
public class Credit extends Account {
    private int creditMax;
    
    /**
     * Constructs a new Credit account with the specified account number, credit limit, and balance.
     *
     * @param accountNumber the account number of the credit account
     * @param creditMax the maximum credit limit of the account
     * @param balance the initial balance of the credit account
     */
    public Credit(String accountNumber, int creditMax, double balance) {
        super(accountNumber, balance);
        this.creditMax = creditMax;
    }
    
    /**
     * Deposits the specified amount into the credit account if it does not exceed the credit limit.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        if (balance + amount > creditMax) {
            System.out.println("Deposit exceeds credit limit");
            return;
        }
        else{
            balance += amount;
        }
    }
    
    /**
     * Withdraws the specified amount from the credit account if sufficient funds are available.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds to withdraw $" + amount);
        }
    }

    /**
     * Gets the maximum credit limit of the account.
     *
     * @return the maximum credit limit
     */
    public int getCreditMax(){
        return this.creditMax;
    }
}
