package tickets.doublevpartnets.tickets.Application.UseCases.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Security.IUserAccessRepository;


@Service
public class FindUserByUsernameUseCase {

    @Autowired
    private IUserAccessRepository repository;

    public UsersAccess execute(String username) {
        return repository.findByUsername(username).orElse(null);
    }
    
}
