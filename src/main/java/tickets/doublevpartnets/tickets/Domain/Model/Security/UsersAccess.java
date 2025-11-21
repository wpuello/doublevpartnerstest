package tickets.doublevpartnets.tickets.Domain.Model.Security;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsersAccess {

    private UUID id;
    private String username;
    private String password; 
    private String role;
    
}
