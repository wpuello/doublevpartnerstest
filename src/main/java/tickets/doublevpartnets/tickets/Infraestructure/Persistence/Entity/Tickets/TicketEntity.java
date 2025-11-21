package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Tickets;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false, length = 500)
    private String descripcion;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "fecha_creacion_ticket", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;
    
}
