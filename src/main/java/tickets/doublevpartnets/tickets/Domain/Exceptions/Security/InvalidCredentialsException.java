package tickets.doublevpartnets.tickets.Domain.Exceptions.Security;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}