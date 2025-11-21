package tickets.doublevpartnets.tickets.Application.UseCases.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class GetAllUsuariosUseCase {
    
    @Autowired
    private IUsuarioRepository repository;

    public List<Usuario> findAll() {
        return repository.findAll();
    }
}
