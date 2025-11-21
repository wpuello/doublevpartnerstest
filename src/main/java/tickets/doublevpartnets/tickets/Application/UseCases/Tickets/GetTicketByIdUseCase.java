package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

@Service
public class GetTicketByIdUseCase {

    @Autowired
    private  ITicketRepository repository;

     public Optional<Ticket> encontrarPorID(UUID id) {
        return repository.findById(id);
    }

    
}
