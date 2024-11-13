/**
 * The Checking class represents a checking account.
 */
public class Checking extends Account implements AccountOperations{

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
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    /**
     * Withdraws the specified amount from the checking account if sufficient funds are available.
     *
     * @param amount the amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Insufficient funds to withdraw $" + amount);
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }
    
    /**
     * Returns the current balance of the checking account.
     *
     * @return the current balance
     */
    @Override
    public double getBalance() {
        return balance;
    }
}
