package tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets;



public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
