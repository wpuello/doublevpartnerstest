package tickets.doublevpartnets.tickets.Domain.Model.Usuarios;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Usuario {

    private UUID id;
    private String nombres;
    private String apellidos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
