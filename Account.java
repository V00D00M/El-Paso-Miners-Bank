/**
 * The Account class represents a generic bank account.
 * This class is abstract and cannot be instantiated directly.
 */
public abstract class Account {
    protected double balance;
    protected String accountNumber;
    private int creditMax;

    /**
     * Constructs a new Account with the specified account number and balance.
     *
     * @param accountNumber the account number of the account
     * @param balance the initial balance of the account
     */
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * Constructs a new Account with the specified account number, credit limit, and balance.
     *
     * @param accountNumber the account number of the account
     * @param creditMax the maximum credit limit of the account
     * @param balance the initial balance of the account
     */
    public Account(String accountNumber, int creditMax, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.creditMax = creditMax;
    }

    /**
     * Gets the account number of the account.
     *
     * @return the account number
     */
    public String getAccountNumber() { return accountNumber; }

    /**
     * Gets the balance of the account.
     *
     * @return the balance
     */
    public double getBalance() { return balance; }

    /**
     * Gets the maximum credit limit of the account.
     *
     * @return the maximum credit limit
     */
    public int getCreditMax() { return creditMax; }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit
     */
    public abstract void deposit(double amount);

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw
     */
    public abstract void withdraw(double amount);

    /**
     * Gets the balance of the account.
     *
     * @param balance the balance to get
     * @return the balance
     */
    public double getBalance(double balance) {
        return balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the new balance of the account
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Sets the balance of the account.
     *
     * @param balance the new balance of the account
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}