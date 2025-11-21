package tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Security;

import java.util.Optional;
import java.util.UUID;
import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;

public interface IUserAccessRepository { //Mi contrato sin Detalles
    
    UsersAccess save(UsersAccess user);
    Optional<UsersAccess> findByUsername(String username);
    Optional<UsersAccess> findById(UUID id);
}
