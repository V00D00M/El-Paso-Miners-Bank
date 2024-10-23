import java.util.List;

public class Customer extends Person {
    private String checkingAccountNumber;
    private double checkingStartingBalance;
    private String savingsAccountNumber;
    private double savingsStartingBalance;
    private String creditAccountNumber;
    private int creditMax;
    private double creditStartingBalance;

    public Customer(String firstName, String lastName, String address, String DOB, String phoneNumber, String email,
                    String checkingAccountNumber, double checkingStartingBalance, String savingsAccountNumber,
                    double savingsStartingBalance, String creditAccountNumber, int creditMax, double creditStartingBalance) {
        super(firstName, lastName, address, DOB, phoneNumber, email);
        this.checkingAccountNumber = checkingAccountNumber;
        this.checkingStartingBalance = checkingStartingBalance;
        this.savingsAccountNumber = savingsAccountNumber;
        this.savingsStartingBalance = savingsStartingBalance;
        this.creditAccountNumber = creditAccountNumber;
        this.creditMax = creditMax;
        this.creditStartingBalance = creditStartingBalance;
    }

    // Getters and Setters for the new fields
    public String getCheckingAccountNumber() {
        return checkingAccountNumber;
    }

    public void setCheckingAccountNumber(String checkingAccountNumber) {
        this.checkingAccountNumber = checkingAccountNumber;
    }

    public double getCheckingStartingBalance() {
        return checkingStartingBalance;
    }

    public void setCheckingStartingBalance(double checkingStartingBalance) {
        this.checkingStartingBalance = checkingStartingBalance;
    }

    public String getSavingsAccountNumber() {
        return savingsAccountNumber;
    }

    public void setSavingsAccountNumber(String savingsAccountNumber) {
        this.savingsAccountNumber = savingsAccountNumber;
    }

    public double getSavingsStartingBalance() {
        return savingsStartingBalance;
    }

    public void setSavingsStartingBalance(double savingsStartingBalance) {
        this.savingsStartingBalance = savingsStartingBalance;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public int getCreditMax() {
        return creditMax;
    }

    public void setCreditMax(int creditMax) {
        this.creditMax = creditMax;
    }

    public double getCreditStartingBalance() {
        return creditStartingBalance;
    }

    public void setCreditStartingBalance(double creditStartingBalance) {
        this.creditStartingBalance = creditStartingBalance;
    }
}
