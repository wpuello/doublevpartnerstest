package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper;

import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Usuarios.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity toEntity(Usuario domain) {
        return UsuarioEntity.builder()
                .id(domain.getId())
                .nombres(domain.getNombres())
                .apellidos(domain.getApellidos())
                .fechaCreacion(domain.getFechaCreacion())
                .fechaActualizacion(domain.getFechaActualizacion())
                .build();
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nombres(entity.getNombres())
                .apellidos(entity.getApellidos())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaActualizacion(entity.getFechaActualizacion())
                .build();
    }
    
}
