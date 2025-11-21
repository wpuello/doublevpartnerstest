package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class GetTicketsByUsuarioUseCase {


    @Autowired
    private ITicketRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public List<Ticket> execute(UUID usuarioId) {

        usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException(
                "El usuario con ID " + usuarioId + " no existe."
            ));

        return repository.findByUsuarioId(usuarioId);
    }
    
}
