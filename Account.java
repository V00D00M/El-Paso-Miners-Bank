public abstract class Account {
    // Account class is abstract, so it cannot be instantiated
    protected int accountNumber;
    protected double balance;

    // this is a test haha

    // Constructor
    public Account(int accountNumber, double balance) {
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

    public int getAccountNumber() {
        return accountNumber;
    }
}