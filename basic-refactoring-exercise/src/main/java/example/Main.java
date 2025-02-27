package example;

import example.exception.InsufficientBudgetException;
import example.exception.InvalidIdException;
import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

public class Main {

    public static void main(String[] args) throws InvalidIdException, InsufficientBudgetException {
        final AccountHolder accountHolder = new AccountHolder("Mario", "Rossi", 1);
        final BankAccount bankAccount = new SimpleBankAccount(accountHolder, 0);
        bankAccount.deposit(accountHolder, 100);
        System.out.println("Current balance is " + bankAccount.getBalance());
        tryToWithdraw(bankAccount, accountHolder, 30);
        tryToWithdraw(bankAccount, accountHolder, 80);
    }

    private static void tryToWithdraw(final BankAccount bankAccount, final AccountHolder holder, double amount) {
        try {
            bankAccount.withdraw(holder, amount);
            System.out.println("Current balance is " + bankAccount.getBalance());
        } catch (InsufficientBudgetException | InvalidIdException e) {
            System.out.println(e.toString());
            System.out.println("Current balance is " + bankAccount.getBalance());
        }
    }
}
