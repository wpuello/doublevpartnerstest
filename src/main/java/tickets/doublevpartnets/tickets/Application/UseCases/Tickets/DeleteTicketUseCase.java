package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets.TicketNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

@Service
public class DeleteTicketUseCase {
    
    @Autowired
    private  ITicketRepository repository;

   public void eliminar(UUID id) {

        var ticket = repository.findById(id)
                .orElseThrow(() ->
                        new TicketNotFoundException("El ticket con ID " + id + " no existe.")
                );

        repository.deleteById(ticket.getId());
    }

}
