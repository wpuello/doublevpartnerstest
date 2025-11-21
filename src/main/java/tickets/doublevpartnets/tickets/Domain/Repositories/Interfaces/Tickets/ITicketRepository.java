package tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;

public interface ITicketRepository { //Mi contrato sin Detalles

    Ticket save(Ticket ticket);

    Optional<Ticket> findById(UUID id);

    List<Ticket> findAll();

    // Paginación (Tickets con Paginacion)
    List<Ticket> findPaginated(int page, int size);

    void deleteById(UUID id);

    List<Ticket> findByStatus(TicketStatus status);

    List<Ticket> findByUsuarioId(UUID usuarioId);

    List<Ticket> findByStatusAndUsuarioId(TicketStatus status, UUID usuarioId);
    
}
