package example.exception;

public class InvalidIdException extends Exception {
    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException() {
        super("ID given is not valid for this operation");
    }
}
