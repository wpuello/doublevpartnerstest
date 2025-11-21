package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;

public class CreateTicketUseCaseTest {

    @Mock
    private ITicketRepository ticketRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private CreateTicketUseCase useCase;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void guardar_CreaTicketExitosamente() {

        UUID usuarioId = UUID.randomUUID();

        Usuario usuarioMock = Usuario.builder()
                .id(usuarioId)
                .nombres("Camilo")
                .apellidos("Zur")
                .fechaCreacion(LocalDateTime.now())
                .build();

        Ticket ticketMock = Ticket.builder()
                .id(UUID.randomUUID())
                .descripcion("Problema con el login de acceso a mi plataforma")
                .usuarioId(usuarioId)
                .status(TicketStatus.ABIERTO)
                .build();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioMock));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketMock);

        Ticket resultado = useCase.guardar("Problema con el login de acceso a mi plataforma", usuarioId);

        assertNotNull(resultado);
        assertEquals("Problema con el login de acceso a mi plataforma", resultado.getDescripcion());
        assertEquals(usuarioId, resultado.getUsuarioId());
        assertEquals(TicketStatus.ABIERTO, resultado.getStatus());
    }

    @Test
    void guardar_LanzaErrorCuandoUsuarioNoExiste() {

        UUID usuarioId = UUID.randomUUID();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNotFoundException.class,
                () -> useCase.guardar("Aqui se presenta un Error", usuarioId)
        );
    }
}
    
