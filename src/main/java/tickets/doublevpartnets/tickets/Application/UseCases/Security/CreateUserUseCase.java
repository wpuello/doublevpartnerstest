package tickets.doublevpartnets.tickets.Application.UseCases.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Security.IUserAccessRepository;

@Service
public class CreateUserUseCase {
    
    @Autowired
    private IUserAccessRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public  UsersAccess create(String username, String password, String role) {

         UsersAccess user =  UsersAccess.builder()
                .username(username)
                .password(encoder.encode(password)) //encripto el password
                .role(role)
                .build();

        return repository.save(user);
    }

    public boolean exists(String username) {
        return repository.findByUsername(username).isPresent();
    }
}

