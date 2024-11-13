package BankUserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankUserTest {
    
    private Customer customer;

    @BeforeEach
    void setUp() {
        // Initialize Customer object with sample data
    }

    @Test
    void testFromCustomerWithAllAccounts() {
        // Setup customer with all types of accounts
        Checking checkingAccount = new Checking("1001", 500.0);
        Savings savingsAccount = new Savings("2002", 300.0);
        Credit creditAccount = new Credit("3003", 1000.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(checkingAccount);
        accounts.add(savingsAccount);
        accounts.add(creditAccount);

        customer = new Customer("12345", "John", "Doe", "01-01-1980", "123 Main St", "555-1234", 750, accounts);

        // Call the fromCustomer method
        BankUser bankUser = BankUser.fromCustomer(customer);

        // Verify that the BankUser object is created correctly
        assertNotNull(bankUser);
        assertEquals(12345, bankUser.getId());
        assertEquals("John", bankUser.getFirstName());
        assertEquals("Doe", bankUser.getLastName());
        assertEquals("01-01-1980", bankUser.getDob());
        assertEquals("123 Main St", bankUser.getAddress());
        assertEquals("555-1234", bankUser.getPhoneNumber());
        assertEquals(750, bankUser.getCreditScore());
        assertEquals(1001, bankUser.getCheckingAccountNumber());
        assertEquals(2002, bankUser.getSavingsAccountNumber());
        assertEquals(3003, bankUser.getCreditAccountNumber());
    }

    @Test
    void testFromCustomerWithNoAccounts() {
        // Setup customer with no accounts
        List<Account> accounts = new ArrayList<>();
        customer = new Customer("12345", "John", "Doe", "01-01-1980", "123 Main St", "555-1234", 750, accounts);

        // Call the fromCustomer method
        BankUser bankUser = BankUser.fromCustomer(customer);

        // Verify that the BankUser object is created correctly, with default account numbers (0)
        assertNotNull(bankUser);
        assertEquals(12345, bankUser.getId());
        assertEquals("John", bankUser.getFirstName());
        assertEquals("Doe", bankUser.getLastName());
        assertEquals("01-01-1980", bankUser.getDob());
        assertEquals("123 Main St", bankUser.getAddress());
        assertEquals("555-1234", bankUser.getPhoneNumber());
        assertEquals(750, bankUser.getCreditScore());
        assertEquals(0, bankUser.getCheckingAccountNumber());
        assertEquals(0, bankUser.getSavingsAccountNumber());
        assertEquals(0, bankUser.getCreditAccountNumber());
    }

    @Test
    void testFromCustomerWithOnlyCheckingAccount() {
        // Setup customer with only Checking account
        Checking checkingAccount = new Checking("1001", 500.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(checkingAccount);

        customer = new Customer("12345", "John", "Doe", "01-01-1980", "123 Main St", "555-1234", 750, accounts);

        // Call the fromCustomer method
        BankUser bankUser = BankUser.fromCustomer(customer);

        // Verify that only the Checking account number is set
        assertNotNull(bankUser);
        assertEquals(1001, bankUser.getCheckingAccountNumber());
        assertEquals(0, bankUser.getSavingsAccountNumber());
        assertEquals(0, bankUser.getCreditAccountNumber());
    }

    @Test
    void testFromCustomerWithOnlySavingsAccount() {
        // Setup customer with only Savings account
        Savings savingsAccount = new Savings("2002", 300.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(savingsAccount);

        customer = new Customer("12345", "John", "Doe", "01-01-1980", "123 Main St", "555-1234", 750, accounts);

        // Call the fromCustomer method
        BankUser bankUser = BankUser.fromCustomer(customer);

        // Verify that only the Savings account number is set
        assertNotNull(bankUser);
        assertEquals(0, bankUser.getCheckingAccountNumber());
        assertEquals(2002, bankUser.getSavingsAccountNumber());
        assertEquals(0, bankUser.getCreditAccountNumber());
    }

    @Test
    void testFromCustomerWithOnlyCreditAccount() {
        // Setup customer with only Credit account
        Credit creditAccount = new Credit("3003", 1000.0);

        List<Account> accounts = new ArrayList<>();
        accounts.add(creditAccount);

        customer = new Customer("12345", "John", "Doe", "01-01-1980", "123 Main St", "555-1234", 750, accounts);

        // Call the fromCustomer method
        BankUser bankUser = BankUser.fromCustomer(customer);

        // Verify that only the Credit account number is set
        assertNotNull(bankUser);
        assertEquals(0, bankUser.getCheckingAccountNumber());
        assertEquals(0, bankUser.getSavingsAccountNumber());
        assertEquals(3003, bankUser.getCreditAccountNumber());
    }
}
//     @Test
//     void testFromCustomer() {

//     }
// }
