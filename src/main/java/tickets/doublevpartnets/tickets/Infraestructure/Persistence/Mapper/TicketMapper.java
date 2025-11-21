package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper;

import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Tickets.TicketEntity;

public class TicketMapper {

    public static TicketEntity toEntity(Ticket t) {
        return TicketEntity.builder()
                .id(t.getId())
                .descripcion(t.getDescripcion())
                .usuarioId(t.getUsuarioId())
                .fechaCreacion(t.getFechaCreacionTicket())
                .fechaActualizacion(t.getFechaActualizacion())
                .status(t.getStatus())
                .build();
    }

    public static Ticket toDomain(TicketEntity e) {
        return Ticket.builder()
                .id(e.getId())
                .descripcion(e.getDescripcion())
                .usuarioId(e.getUsuarioId())
                .fechaCreacionTicket(e.getFechaCreacion())
                .fechaActualizacion(e.getFechaActualizacion())
                .status(e.getStatus())
                .build();
    }
    
}
