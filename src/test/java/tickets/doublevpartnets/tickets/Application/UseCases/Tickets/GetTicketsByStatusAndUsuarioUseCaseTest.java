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
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

public class GetTicketsByStatusAndUsuarioUseCaseTest {

    @Mock
    private ITicketRepository ticketRepo;

    @Mock
    private IUsuarioRepository usuarioRepo;

    @InjectMocks
    private GetTicketsByStatusAndUsuarioUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findSU_StatusYUsuarioCorrectos_RetornaLista() {

        UUID userId = UUID.randomUUID();

        Usuario user = Usuario.builder().id(userId).nombres("Ana").apellidos("Lopez").build();
        when(usuarioRepo.findById(userId)).thenReturn(Optional.of(user));

        Ticket t1 = Ticket.builder().descripcion("T1").status(TicketStatus.ABIERTO).usuarioId(userId).build();

        when(ticketRepo.findByStatusAndUsuarioId(TicketStatus.ABIERTO, userId))
                .thenReturn(List.of(t1));

        var resultado = useCase.findSU("ABIERTO", userId);

        assertEquals(1, resultado.size());
        assertEquals("T1", resultado.get(0).getDescripcion());
    }

    @Test
    void findSU_StatusInvalido_LanzaExcepcion() {
        UUID id = UUID.randomUUID();

        assertThrows(
            ValidationException.class,
            () -> useCase.findSU("INVALIDO", id)
        );
    }

    @Test
    void findSU_StatusNull_LanzaExcepcion() {
        UUID id = UUID.randomUUID();

        assertThrows(
            ValidationException.class,
            () -> useCase.findSU(null, id)
        );
    }

    @Test
    void findSU_UsuarioIdNull_LanzaExcepcion() {
        assertThrows(
            ValidationException.class,
            () -> useCase.findSU("ABIERTO", null)
        );
    }

    @Test
    void findSU_UsuarioNoExiste_LanzaExcepcion() {
        UUID id = UUID.randomUUID();

        when(usuarioRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(
            UsuarioNotFoundException.class,
            () -> useCase.findSU("ABIERTO", id)
        );
    }
    
}
