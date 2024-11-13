package SavingsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsTest {

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

        // Check that the error message is printed
        assertTrue(outContent.toString().contains("Insufficient funds to withdraw"), 
                "Expected 'Insufficient funds' message to be printed");

        // Balance should remain unchanged at 1000.00
        assertEquals(1000.00, savingsAccount.getBalance(), "Balance should remain 1000.00 after failed withdrawal");
    }

    @Test
    void testWithdrawExactAmount() {
        // Initial balance is 1000.00
        // Withdraw the exact amount of 1000.00, balance should become 0.00
        savingsAccount.withdraw(1000.00);
        assertEquals(0.00, savingsAccount.getBalance(), "Balance should be 0.00 after withdrawing the exact amount");
    }

    @Test
    void testMultipleDepositsAndWithdrawals() {
        // Initial balance is 1000.00
        savingsAccount.deposit(500.00);  // New balance should be 1500.00
        savingsAccount.withdraw(200.00); // New balance should be 1300.00
        savingsAccount.deposit(100.00);  // New balance should be 1400.00

        // Final balance should be 1400.00 after all transactions
        assertEquals(1400.00, savingsAccount.getBalance(), "Balance should be 1400.00 after multiple transactions");
    }
}
