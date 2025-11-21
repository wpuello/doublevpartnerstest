package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Adapter.Usuarios;

import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Usuarios.UsuarioEntity;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper.UsuarioMapper;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Usuarios.DataUsuarioRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

    @Autowired
    private DataUsuarioRepository repository;

    @Override
    public Usuario save(Usuario user) {
        UsuarioEntity entity = UsuarioMapper.toEntity(user);
        return UsuarioMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return repository.findById(id).map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll()
                .stream()
                .map(UsuarioMapper::toDomain)
                .toList();
    }

    @Override
    public Usuario update(Usuario user) {
        UsuarioEntity entity = UsuarioMapper.toEntity(user);
        return UsuarioMapper.toDomain(repository.save(entity));
    }
}
