package tickets.doublevpartnets.tickets.Domain.Model.Tickets;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;


@Data
@Builder
@AllArgsConstructor
public class Ticket {
    
    private UUID id;
    private String descripcion;
    private UUID usuarioId;
    private LocalDateTime fechaCreacionTicket;
    private LocalDateTime fechaActualizacion;
    private TicketStatus status;
}
