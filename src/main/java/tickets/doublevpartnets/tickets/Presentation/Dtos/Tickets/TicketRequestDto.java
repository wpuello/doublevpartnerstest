package tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets;

import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TicketRequestDto {
    
    @Schema(example = "Problema con el inicio de sesión del usuario")
    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
    private String descripcion;

    @Schema(example = "c8a44f8d-3df6-4db9-8b41-4b295eef1122")
    @NotNull(message = "El usuarioId es obligatorio.")
    private UUID usuarioId;
}
