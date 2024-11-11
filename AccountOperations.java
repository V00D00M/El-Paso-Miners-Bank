/**
 * Interface for AccountOperations
 * Provides methods for depositing, withdrawing, and
 * getting the balance of an account.
 */
public interface AccountOperations {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
}
