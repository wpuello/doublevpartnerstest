package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Tickets;

import java.util.List;
import java.util.UUID;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Tickets.TicketEntity;

public interface DataTicketRepository extends JpaRepository<TicketEntity, UUID>  {
    
    List<TicketEntity> findByStatus(TicketStatus status);

    List<TicketEntity> findByUsuarioId(UUID usuarioId);

    List<TicketEntity> findByStatusAndUsuarioId(TicketStatus status, UUID usuarioId);
}
