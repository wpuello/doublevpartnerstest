package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Security;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "users_access")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersAccessEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;  // encripto con BCrypt

    @Column(nullable = false)
    private String role; // ADMIN
}
