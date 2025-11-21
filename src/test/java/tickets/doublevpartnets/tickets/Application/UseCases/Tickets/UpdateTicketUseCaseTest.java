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
import tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets.TicketNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

public class UpdateTicketUseCaseTest {

    @Mock
    private ITicketRepository repository;

    @InjectMocks
    private UpdateTicketUseCase useCase;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    private Ticket crearTicketExistente(UUID id) {
        return Ticket.builder()
            .id(id)
            .descripcion("Descripcion inicial")
            .usuarioId(UUID.randomUUID())
            .fechaCreacionTicket(LocalDateTime.now())
            .status(TicketStatus.ABIERTO)
            .build();
    }

    @Test
    void actualizar_ActualizaExitosamente() {

        UUID id = UUID.randomUUID();

        Ticket ticketExistente = crearTicketExistente(id);

        when(repository.findById(id)).thenReturn(Optional.of(ticketExistente));
        when(repository.save(any(Ticket.class))).thenAnswer(invoc -> invoc.getArgument(0));

        Ticket result = useCase.actualizar(id, "Nueva descripcion", "CERRADO");

        assertNotNull(result);
        assertEquals("Nueva descripcion", result.getDescripcion());
        assertEquals(TicketStatus.CERRADO, result.getStatus());
    }

    @Test
    void actualizar_TicketNoExiste() {

        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
            TicketNotFoundException.class,
            () -> useCase.actualizar(id, "desc", "ABIERTO")
        );
    }

}
