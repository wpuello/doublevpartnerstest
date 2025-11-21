package tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios;


public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
}