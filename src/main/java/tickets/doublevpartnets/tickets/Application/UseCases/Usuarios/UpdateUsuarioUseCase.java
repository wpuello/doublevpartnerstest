package tickets.doublevpartnets.tickets.Application.UseCases.Usuarios;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class UpdateUsuarioUseCase {

    @Autowired
    private IUsuarioRepository repository;

    public Usuario update(UUID id, String nombres, String apellidos) {


        var user = repository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + id + " no existe"));

        Usuario actualizado = Usuario.builder()
                .id(user.getId())
                .nombres(nombres)
                .apellidos(apellidos)
                .fechaCreacion(user.getFechaCreacion())
                .fechaActualizacion(LocalDateTime.now())
                .build();

        return repository.update(actualizado);
    }
    
}
