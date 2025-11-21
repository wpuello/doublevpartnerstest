package tickets.doublevpartnets.tickets.Presentation.Dtos.Security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDto {

    @Schema(example = "admin")
    @NotNull(message = "El usuario es obligatorio.")
    @NotBlank(message = "La descripción es obligatoria.")
    private String username;

    @Schema(example = "admin123")
    @NotNull(message = "El password es obligatorio.")
    @NotBlank(message = "La descripción es obligatoria.")
    private String password;
}
