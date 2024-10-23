public class Checking extends Account {
    // private double overdraftLimit;
    
    public Checking(String accountNumber, double balance) {
        super(accountNumber, balance);
    }
    
    public void deposit(double amount) {
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (balance > amount) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds to withdraw $" + amount);
        }

    
}    
}
