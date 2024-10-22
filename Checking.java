public class Checking extends Account {
    private double overdraftLimit;
    
    public Checking(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds");
        }
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
}
