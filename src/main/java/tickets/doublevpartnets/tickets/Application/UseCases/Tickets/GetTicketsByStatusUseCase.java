package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

@Service
public class GetTicketsByStatusUseCase {

    @Autowired
    private  ITicketRepository repository;


    public List<Ticket> findbystatus(String status) {


       if (status == null || status.isBlank()) {
            throw new ValidationException("El status es obligatorio.");
        }

       String statuses = status.trim().toUpperCase();

        if (!statuses.equals("ABIERTO") && !statuses.equals("CERRADO")) {
            throw new ValidationException("El status solo puede ser ABIERTO o CERRADO.");
        }

        return repository.findByStatus(TicketStatus.valueOf(statuses));
    }
    
}
