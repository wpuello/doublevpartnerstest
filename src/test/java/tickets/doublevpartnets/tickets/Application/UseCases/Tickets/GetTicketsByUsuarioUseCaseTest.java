package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

public class GetTicketsByUsuarioUseCaseTest {

    @Mock
    private ITicketRepository ticketRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private GetTicketsByUsuarioUseCase useCase;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_UsuarioExiste_RetornaListaDeTickets() {

        UUID usuarioId = UUID.randomUUID();

        Usuario usuarioMock = Usuario.builder()
                .id(usuarioId)
                .nombres("Juan")
                .apellidos("Perez")
                .build();

        Ticket ticket1 = Ticket.builder().descripcion("A").usuarioId(usuarioId).build();
        Ticket ticket2 = Ticket.builder().descripcion("B").usuarioId(usuarioId).build();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioMock));
        when(ticketRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(ticket1, ticket2));

        List<Ticket> resultado = useCase.execute(usuarioId);

        assertEquals(2, resultado.size());
    }

    @Test
    void execute_UsuarioNoExiste_LanzaExcepcion() {

        UUID usuarioId = UUID.randomUUID();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNotFoundException.class,
                () -> useCase.execute(usuarioId)
        );
    }
    
}
