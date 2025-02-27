package example.model;

import example.exception.InsufficientBudgetException;
import example.exception.InvalidIdException;

/**
 * This interface defines the concept of a very basic bank account.
 */
public interface BankAccount {

    /**
     * Allows to know who is the holder of this bank account
     * @return the AccountHolder instance related to this bank account.
     */
    AccountHolder getHolder();

    /**
     * Returns the current balance of the bank account
     * @return the current balance
     */
    double getBalance();

    /**
     * Allows the deposit of an amount on the account, if the given userID corresponds to the register holder ID
     * of the bank account. This ID acts like an "identification token" .
     *
     * @param holder the user that wants do the deposit
     * @param amount the amount of the deposit
     */
    void deposit(final AccountHolder holder, double amount) throws InvalidIdException;

    /**
     * Allows the withdrawal of an amount from the account, if the given userID corresponds to the register holder ID
     * of the bank account. This ID acts like an "identification token" .
     *
     * @param holder the user that wants do the withdrawal
     * @param amount the amount of the withdrawal
     */
    void withdraw(final AccountHolder holder, double amount) throws InvalidIdException, InsufficientBudgetException;
}
