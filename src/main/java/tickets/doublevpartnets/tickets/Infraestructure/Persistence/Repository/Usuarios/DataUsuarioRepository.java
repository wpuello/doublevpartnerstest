package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Usuarios;

import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Usuarios.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DataUsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    
}
