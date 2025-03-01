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

    public static final int FIRST_DEPOSIT_AMOUNT = 100;
    public static final int INVALID_WITHDRAW_AMOUNT = 120;
    public static final int VALID_WITHDRAW_AMOUNT = 70;

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
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongIDDeposit() throws InvalidIdException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        assertThrows(InvalidIdException.class,
                () -> bankAccount.deposit(falseAccountHolder, VALID_WITHDRAW_AMOUNT),
                "Expected InvalidIdException because of different id");
    }

    @Test
    void testWithdraw() throws InvalidIdException, InsufficientBudgetException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder, VALID_WITHDRAW_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT - VALID_WITHDRAW_AMOUNT - bankAccount.getWithdrawalFee(),
                bankAccount.getBalance());
    }

    @Test
    void testWrongIDWithdraw() throws InvalidIdException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        assertThrows(InvalidIdException.class,
                () -> bankAccount.withdraw(falseAccountHolder, VALID_WITHDRAW_AMOUNT),
                "Expected InvalidIdException because of different id");
    }

    @Test
    void testInsufficientBudgetWithdraw() throws InvalidIdException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        assertThrows(InsufficientBudgetException.class,
                () -> bankAccount.withdraw(accountHolder, INVALID_WITHDRAW_AMOUNT),
                "Asked 120 with budget 100, expected InsufficientBudgetException");
    }

    @Test
    void testInsufficientBudgetWithdrawWithFEE() throws InvalidIdException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        assertThrows(InsufficientBudgetException.class,
                () -> bankAccount.withdraw(accountHolder,
                        FIRST_DEPOSIT_AMOUNT + (bankAccount.getWithdrawalFee() == 0 ? 1 : bankAccount.getWithdrawalFee())),
                "Asked 100 with budget 100, expected InsufficientBudgetException because of FEE");
    }

    @Test
    void testInsufficientBudgetWithdrawHandled() throws InvalidIdException {
        bankAccount.deposit(accountHolder, FIRST_DEPOSIT_AMOUNT);
        try {
            bankAccount.withdraw(accountHolder, INVALID_WITHDRAW_AMOUNT);
        } catch (InsufficientBudgetException e) {
            assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
        }
        assertEquals(FIRST_DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
