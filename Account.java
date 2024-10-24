public abstract class Account {
    // Account class is abstract, so it cannot be instantiated
    protected double balance;
    protected String accountNumber;
    private int creditMax;

    // Constructor
    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(String accountNumber, int creditMax, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.creditMax = creditMax;
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public int getCreditMax() { return creditMax; }

    // Abstract method
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    // Getter
    public double getBalance(double balance) {
        return balance;
    }

    // Setter
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}