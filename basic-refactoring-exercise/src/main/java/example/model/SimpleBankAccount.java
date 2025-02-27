package example.model;

import example.exception.InsufficientBudgetException;
import example.exception.InvalidIdException;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    final static double WITHDRAWAL_FEE = 0;

    private double balance;
    private final AccountHolder holder;
    private final double withdrawFee;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
        this.withdrawFee = WITHDRAWAL_FEE;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final AccountHolder holder, final double amount) throws InvalidIdException {
        checkUser(holder.getId());
        this.balance += amount;
    }

    @Override
    public void withdraw(final AccountHolder holder, final double amount) throws InvalidIdException, InsufficientBudgetException {
        checkUser(holder.getId());
        double amountWithFee = amount;
        amountWithFee += WITHDRAWAL_FEE;
        checkBudgetIsSufficient(amountWithFee);
        this.balance -= amountWithFee;
    }

    public double getWithdrawalFee() { return this.withdrawFee; }

    private void checkBudgetIsSufficient(final double amount) throws InsufficientBudgetException {
        if (!isWithdrawAllowed(amount)) {
            throw new InsufficientBudgetException();
        }
    }

    private void checkUser(int userID) throws InvalidIdException {
        if (!isIDValid(userID)) {
            throw new InvalidIdException();
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount;
    }

    private boolean isIDValid(final int id) {
        return this.holder.getId() == id;
    }

}
