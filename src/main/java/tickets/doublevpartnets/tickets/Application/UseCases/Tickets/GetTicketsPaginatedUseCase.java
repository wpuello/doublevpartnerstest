package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;


@Service
public class GetTicketsPaginatedUseCase {

    @Autowired
    private  ITicketRepository repository;

    public List<Ticket> encontrarpaginado(int page, int size) {
        return repository.findPaginated(page, size); //metodo Epecifico de JPA para poder entregar listas Paginadas
    }
    
}
