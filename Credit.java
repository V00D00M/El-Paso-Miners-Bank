/**
 * The Credit class represents a credit account with a maximum credit limit.
 */
public class Credit extends Account implements interestBearing{
    private int creditMax;
    private double interestRate; //Assume we have an interest rate for the credit account
    
    /**
     * Constructs a new Credit account with the specified account number, credit limit, and balance.
     *
     * @param accountNumber the account number of the credit account
     * @param creditMax the maximum credit limit of the account
     * @param balance the initial balance of the credit account
     * @param interestRate the interest rate for the credit account
     */
    public Credit(String accountNumber, int creditMax, double balance, double interestRate) {
        super(accountNumber, balance);
        this.creditMax = creditMax;
        this.interestRate = interestRate;
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

    public void withdraw(double amount) {
        System.out.println("Withdrawals are not allowed from a credit account.");
    }
    
    /**
     * Makes a payment to the credit account.
     *
     * @param amount the amount to pay
     */
    public void makePayment(double amount) {
        if (amount > 0) {
            balance -= amount;
            if (balance < 0) {
                balance = 0;
            }
        } else {
            System.out.println("Invalid payment amount");
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

    /**
     * Calculates interest for the credit account
     */
    @Override
    public void calculateInterest() {
        //Apply interest to the current balance 
        double interest = balance * interestRate;
        balance = balance + interest;

    }
}
