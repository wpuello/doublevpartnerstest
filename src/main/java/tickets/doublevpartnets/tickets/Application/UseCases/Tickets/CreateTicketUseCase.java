package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class CreateTicketUseCase {

    @Autowired
    private  ITicketRepository repository;

    @Autowired
    private IUsuarioRepository repousuario;

    @CacheEvict(value = "TicketsPorUsuario", key = "#usuarioId")
    public Ticket guardar(String descripcion, UUID usuarioId) {

         var user = repousuario.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException("El usuario con ID " + usuarioId + " no existe, favor corregir"));

        Ticket ticketguardado = Ticket.builder()
                .descripcion(descripcion)
                .usuarioId(user.getId())
                .fechaCreacionTicket(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .status(TicketStatus.ABIERTO)// Abierto por Defecto cuando es primera vez
                .build();

        return repository.save(ticketguardado);
    }
    
}
