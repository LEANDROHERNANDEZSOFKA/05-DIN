package co.sofka.exceptions;

public class InvalidFundsException extends RuntimeException {
    public InvalidFundsException(String message) {
        super(message);
    }
}
