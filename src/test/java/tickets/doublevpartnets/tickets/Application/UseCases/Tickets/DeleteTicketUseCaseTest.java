package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets.TicketNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

public class DeleteTicketUseCaseTest {
    
    @Mock
    private ITicketRepository ticketRepository;

    @InjectMocks
    private DeleteTicketUseCase useCase;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminar_Exitoso() {

        UUID id = UUID.randomUUID();

        Ticket ticketMock = Ticket.builder()
                .id(id)
                .descripcion("desc")
                .usuarioId(UUID.randomUUID())
                .build();

        when(ticketRepository.findById(id)).thenReturn(Optional.of(ticketMock));

        useCase.eliminar(id);

        verify(ticketRepository, times(1)).deleteById(id);
    }

    @Test
    void eliminar_TicketNoExiste() {

        UUID id = UUID.randomUUID();

        when(ticketRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(
                TicketNotFoundException.class,
                () -> useCase.eliminar(id)
        );

        verify(ticketRepository, never()).deleteById(any());
    }
}
