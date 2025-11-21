package tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;


@Data
public class TicketResponseDto {

    @Schema(example = "d290f1ee-6c54-4b01-90e6-d701748f0851")
    private UUID id;

     @Schema(example = "Requiero soporte para mi PC asignado por problemas en la pantalla")
    private String descripcion;

    @Schema(example = "c8a44f8d-3df6-4db9-8b41-4b295eef1122")
    private UUID usuarioId;

    @Schema(example = "2025-11-20T14:35:50")
    private LocalDateTime fechaCreacionTicket;

    @Schema(example = "2025-11-20T14:35:50")
    private LocalDateTime fechaActualizacion;

    private String status;

    public static TicketResponseDto desdeDomain(Ticket t) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(t.getId());
        dto.setDescripcion(t.getDescripcion());
        dto.setUsuarioId(t.getUsuarioId());
        dto.setFechaCreacionTicket(t.getFechaCreacionTicket());
        dto.setFechaActualizacion(t.getFechaActualizacion());
        dto.setStatus(t.getStatus().name());
        return dto;
    }
    
}
