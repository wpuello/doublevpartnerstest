package tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class UsuarioRequestDto {

    @Schema(example = "WILLIAM")
    @NotBlank(message = "El nombre es requerido")
    private String nombres;

    @Schema(example = "PUELLO ELLES")
    @NotBlank(message = "El apellido es requeido")
    private String apellidos;
}
