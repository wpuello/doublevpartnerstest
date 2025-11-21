package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class GetTicketsByStatusAndUsuarioUseCase {

     @Autowired
    private  ITicketRepository repository;

    @Autowired
    private IUsuarioRepository usuarioRepo;


    public List<Ticket> findSU(String status, UUID usuarioId) {

        // Validar status
        if (status == null || status.isBlank()) {
            throw new ValidationException("El status es obligatorio.");
        }


         String statuses = status.trim().toUpperCase();

        if (!statuses.equals("ABIERTO") && !statuses.equals("CERRADO")) {
            throw new ValidationException("El status solo puede ser ABIERTO o CERRADO.");
        }

        // Validar usuarioId
        if (usuarioId == null) {
            throw new ValidationException("El usuarioId es obligatorio.");
        }

        // Validar existencia del usuario
        usuarioRepo.findById(usuarioId)
            .orElseThrow(() -> new UsuarioNotFoundException(
                "El usuario con ID " + usuarioId + " no existe."
            ));

        return repository.findByStatusAndUsuarioId(TicketStatus.valueOf(statuses), usuarioId);
    }
    
}
