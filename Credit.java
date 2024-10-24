public class Credit extends Account {
    private int creditMax;
    
    public Credit(String accountNumber, int creditMax, double balance) {
        super(accountNumber, balance);
        this.creditMax = creditMax;
    }
    
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
        if (balance >= amount) {
            balance -= amount;
        }
        else {
            System.out.println("Insufficient funds to withdraw $" + amount);
        }
    }
}
