package tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Pattern;


@Data
public class TicketRequestUpdateDto {

    @Schema(example = "Problema con el inicio de sesión del usuario")
    @NotBlank(message = "La descripción es obligatoria.")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres.")
    private String descripcion;

    @Schema(example = "c8a44f8d-3df6-4db9-8b41-4b295eef1122")
    @NotNull(message = "El usuarioId es obligatorio.")
    private UUID usuarioId;


    @Schema(
        example = "ABIERTO",
        description = "Estatus del ticket. Valores permitidos: ABIERTO, CERRADO"
    )
    @NotBlank(message = "El status es obligatorio.")
    @Pattern(
        regexp = "ABIERTO|CERRADO",
        message = "El status solo puede ser ABIERTO o CERRADO."
    )
    private String status;
    
}
