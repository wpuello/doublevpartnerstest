package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Adapter.Tickets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Tickets.TicketEntity;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper.TicketMapper;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;//CONTRATO
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Repository.Tickets.DataTicketRepository; //Adaptador

@Repository
@RequiredArgsConstructor
public class TicketRepositoryImpl implements ITicketRepository { //Adaptador hacia el dominio

    @Autowired
    private  DataTicketRepository repo;

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity entity = TicketMapper.toEntity(ticket);
        return TicketMapper.toDomain(repo.save(entity));
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        return repo.findById(id).map(TicketMapper::toDomain);
    }

    @Override
    public List<Ticket> findAll() {
        return repo.findAll().stream()
                .map(TicketMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }

    // Con Paginación 
    @Override
    public List<Ticket> findPaginated(int page, int size) {
        return repo.findAll(PageRequest.of(page, size))
                .map(TicketMapper::toDomain)
                .getContent();
    }

    @Override
    public List<Ticket> findByStatus(TicketStatus status) {
        return repo.findByStatus(status).stream()
                .map(TicketMapper::toDomain)
                .toList();
    }

    @Override
    public List<Ticket> findByUsuarioId(UUID usuarioId) {
        return repo.findByUsuarioId(usuarioId).stream()
                .map(TicketMapper::toDomain)
                .toList();
    }

    @Override
    public List<Ticket> findByStatusAndUsuarioId(TicketStatus status, UUID usuarioId) {
        return repo.findByStatusAndUsuarioId(status, usuarioId).stream()
                .map(TicketMapper::toDomain)
                .toList();
    }
    
}
