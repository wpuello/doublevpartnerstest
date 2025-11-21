package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Adapter.Security;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Security.IUserAccessRepository;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Security.UsersAccessEntity;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper.UserAccessMapper;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Security.DataUsersAccessRepository;

@Repository
public class UserAccessRepositoryImpl implements IUserAccessRepository {

    @Autowired
    private DataUsersAccessRepository repository;

    @Override
    public UsersAccess save(UsersAccess user) {
        UsersAccessEntity entity = UserAccessMapper.toEntity(user);
        return UserAccessMapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<UsersAccess> findByUsername(String username) {
        return repository.findByUsername(username).map(UserAccessMapper::toDomain);
    }

    @Override
    public Optional<UsersAccess> findById(UUID id) {
        return repository.findById(id).map(UserAccessMapper::toDomain);
    }

}
