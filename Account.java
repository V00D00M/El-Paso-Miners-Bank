public abstract class Account {
    // Account class is abstract, so it cannot be instantiated
    protected String accountNumber;
    protected double balance;

    // Constructor
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Abstract method
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    // Getter
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}