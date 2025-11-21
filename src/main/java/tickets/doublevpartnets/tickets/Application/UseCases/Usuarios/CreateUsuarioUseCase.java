package tickets.doublevpartnets.tickets.Application.UseCases.Usuarios;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;


@Service
public class CreateUsuarioUseCase {

    @Autowired
    private IUsuarioRepository repository;

    public Usuario create(String nombres, String apellidos) {
        Usuario usuario = Usuario.builder()
                .nombres(nombres)
                .apellidos(apellidos)
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();

        return repository.save(usuario);
    }
    
}
