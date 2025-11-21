package tickets.doublevpartnets.tickets.Application.UseCases.Usuarios;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class GetUsuarioByIdUseCase {
    
    @Autowired
    private IUsuarioRepository repository;

    public Optional<Usuario> find(UUID id) {
        return repository.findById(id);
    }
}
