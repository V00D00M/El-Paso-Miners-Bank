import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

package SavingsTest;

public class javaTest {

    private Savings savingsAccount;

    @BeforeEach
    void setUp() {
        // Create a new savings account with account number "12345" and initial balance 1000.00
        savingsAccount = new Savings("12345", 1000.00);
    }

    @Test
    void testDeposit() {
        // Initial balance is 1000.00
        // Deposit 500.00, new balance should be 1500.00
        savingsAccount.deposit(500.00);
        assertEquals(1500.00, savingsAccount.getBalance(), "Balance should be 1500.00 after deposit");
    }

    @Test
    void testWithdraw() {
        // Initial balance is 1000.00
        // Withdraw 300.00, new balance should be 700.00
        savingsAccount.withdraw(300.00);
        assertEquals(700.00, savingsAccount.getBalance(), "Balance should be 700.00 after withdrawal");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        // Initial balance is 1000.00
        // Trying to withdraw 1500.00 should not change the balance
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent)); // Capture System.out output

        savingsAccount.withdraw(1500.00);  // Attempt withdrawal with insufficient funds

        assertEquals(1000.00, savingsAccount.getBalance(), "Balance should remain 1000.00 after failed withdrawal");
        assertTrue(outContent.toString().contains("Insufficient funds"), "Should print 'Insufficient funds' message");
    }

    @Test
    void testWithdrawExactBalance() {
        // Initial balance is 1000.00
        // Withdraw 1000.00, new balance should be 0.00
        savingsAccount.withdraw(1000.00);
        assertEquals(0.00, savingsAccount.getBalance(), "Balance should be 0.00 after withdrawing the exact balance");
    }

    @Test
    void testMultipleDepositsAndWithdrawals() {
        // Initial balance is 1000.00
        // Perform multiple deposits and withdrawals
        savingsAccount.deposit(200.00);
        savingsAccount.withdraw(100.00);
        savingsAccount.deposit(300.00);
        savingsAccount.withdraw(400.00);
        // Final balance should be 1000.00 + 200.00 - 100.00 + 300.00 - 400.00 = 1000.00
        assertEquals(1000.00, savingsAccount.getBalance(), "Balance should be 1000.00 after multiple transactions");
    }
}