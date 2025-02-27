package example.exception;

public class InsufficientBudgetException extends Exception {
    public InsufficientBudgetException(String message) {
        super(message);
    }

    public InsufficientBudgetException() {
        super("Budget is not sufficient to perform this operation");
    }
}
