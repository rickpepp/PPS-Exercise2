import example.exception.InsufficientBudgetException;
import example.exception.InvalidIdException;
import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private AccountHolder falseAccountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        falseAccountHolder = new AccountHolder("Ciao", "Ciao", 2);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() throws InvalidIdException {
        bankAccount.deposit(accountHolder, 100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testWrongIDDeposit() throws InvalidIdException {
        bankAccount.deposit(accountHolder, 100);
        assertThrows(InvalidIdException.class,
                () -> bankAccount.deposit(falseAccountHolder, 50),
                "Expected InvalidIdException because of different id");
    }

    @Test
    void testWithdraw() throws InvalidIdException, InsufficientBudgetException {
        bankAccount.deposit(accountHolder, 100);
        bankAccount.withdraw(accountHolder, 70);
        assertEquals(30, bankAccount.getBalance());
    }

    @Test
    void testWrongIDWithdraw() throws InvalidIdException {
        bankAccount.deposit(accountHolder, 100);
        assertThrows(InvalidIdException.class,
                () -> bankAccount.withdraw(falseAccountHolder, 70),
                "Expected InvalidIdException because of different id");
    }

    @Test
    void testInsufficientBudgetWithdraw() throws InvalidIdException {
        bankAccount.deposit(accountHolder, 100);
        assertThrows(InsufficientBudgetException.class,
                () -> bankAccount.withdraw(accountHolder, 120),
                "Asked 120 with budget 100, expected InsufficientBudgetException");
    }

    @Test
    void testInsufficientBudgetWithdrawHandled() throws InvalidIdException {
        bankAccount.deposit(accountHolder, 100);
        try {
            bankAccount.withdraw(accountHolder, 120);
        } catch (InsufficientBudgetException e) {
            assertEquals(100, bankAccount.getBalance());
        }
        assertEquals(100, bankAccount.getBalance());
    }
}
