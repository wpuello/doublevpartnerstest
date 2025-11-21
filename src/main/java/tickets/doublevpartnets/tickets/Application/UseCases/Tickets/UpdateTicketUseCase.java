package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets.TicketNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;


@Service
public class UpdateTicketUseCase {

    @Autowired
    private  ITicketRepository repository;


     public Ticket actualizar(UUID id, String descripcion, String status) {

        Ticket ticketExistente = repository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("El ticket con ID " + id + " no existe."));

    
        if (descripcion == null || descripcion.isBlank()) {
            throw new ValidationException("La descripción es obligatoria.");
        }

        if (status == null || status.isBlank()) {
            throw new ValidationException("El status es obligatorio.");
        }

        if (!status.equals("ABIERTO") && !status.equals("CERRADO")) {
            throw new ValidationException("El status solo puede ser ABIERTO o CERRADO.");
        }

       
        Ticket actualizado = Ticket.builder()
                .id(ticketExistente.getId())
                .descripcion(descripcion)
                .usuarioId(ticketExistente.getUsuarioId())
                .fechaCreacionTicket(ticketExistente.getFechaCreacionTicket())
                .fechaActualizacion(LocalDateTime.now())
                .status(TicketStatus.valueOf(status))
                .build();

        return repository.save(actualizado);
    }

    
}
