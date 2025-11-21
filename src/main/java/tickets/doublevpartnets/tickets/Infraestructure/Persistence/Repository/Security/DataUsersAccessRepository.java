package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Security;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Security.UsersAccessEntity;

public interface DataUsersAccessRepository extends JpaRepository<UsersAccessEntity, UUID> {

    Optional<UsersAccessEntity> findByUsername(String username);
    
}
