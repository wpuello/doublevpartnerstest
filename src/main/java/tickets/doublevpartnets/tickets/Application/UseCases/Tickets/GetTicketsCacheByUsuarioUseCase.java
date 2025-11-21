package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

@Service
public class GetTicketsCacheByUsuarioUseCase {

   
    private final ITicketRepository ticketRepository;

    
    private final IUsuarioRepository usuarioRepository;


    public GetTicketsCacheByUsuarioUseCase(ITicketRepository ticketRepository,
                                           IUsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Cacheable(value = "ticketsPorUsuario", key = "#usuarioId")
    public List<Ticket> obtenerTicketsPorUsuario(UUID usuarioId) {


        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(
                        "El usuario con ID " + usuarioId + " no existe"));

        System.out.println("Obteniendo tickets desde la BASE DE DATOS...");

        return ticketRepository.findByUsuarioId(usuarioId);
    }
    
}
