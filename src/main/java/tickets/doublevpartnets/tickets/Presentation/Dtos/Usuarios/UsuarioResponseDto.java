package tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;

@Data
public class UsuarioResponseDto {

    private UUID id;
    private String nombres;
    private String apellidos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public static UsuarioResponseDto desdeDomain(Usuario u) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
                dto.setId(u.getId());
                dto.setNombres(u.getNombres());
                dto.setApellidos(u.getApellidos());
                dto.setFechaCreacion(u.getFechaCreacion());
                dto.setFechaActualizacion(u.getFechaActualizacion());
                return dto;
    }

}
