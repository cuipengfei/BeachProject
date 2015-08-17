package bank.domain.exception;

public class OverdrawException extends RuntimeException {
    public OverdrawException(String message) {
        super(message);
    }
}
