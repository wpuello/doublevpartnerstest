package tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;

public interface IUsuarioRepository { //Este es mi Contrato
    
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(UUID id);
    List<Usuario> findAll();
    Usuario update(Usuario usuario);
}
