package tickets.doublevpartnets.tickets.Application.UseCases.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Security.InvalidCredentialsException;
import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Security.IUserAccessRepository;
import tickets.doublevpartnets.tickets.Infraestructure.Security.JwtUtil;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Security.TokenAuthResponseDto;

@Service
public class LoginUseCase {

    @Autowired
    private IUserAccessRepository repository;

    @Value("${jwt.expiration}")
    private long expiration;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public TokenAuthResponseDto login(String username, String password) {

        // Busco el usuario
        UsersAccess user = repository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Usuario o contraseña inválidos."));

        // Valido contraseña
        if (!encoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Usuario o contraseña inválidos.");
        }

        // Genero el token
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
       

        return new TokenAuthResponseDto(
            token,
            "Bearer",
            expiration
        );

    }
    
}
