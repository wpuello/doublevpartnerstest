package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Model.Tickets.Ticket;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;

public class GetTicketsByStatusUseCaseTest {
    
    @Mock
    private ITicketRepository repository;

    @InjectMocks
    private GetTicketsByStatusUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findbystatus_StatusCorrecto_RetornaLista() {
        Ticket ticket1 = Ticket.builder().descripcion("Uno").status(TicketStatus.ABIERTO).build();
        Ticket ticket2 = Ticket.builder().descripcion("Dos").status(TicketStatus.ABIERTO).build();

        when(repository.findByStatus(TicketStatus.ABIERTO))
            .thenReturn(List.of(ticket1, ticket2));

        var resultado = useCase.findbystatus("ABIERTO");

        assertEquals(2, resultado.size());
        assertEquals(TicketStatus.ABIERTO, resultado.get(0).getStatus());
    }

    @Test
    void findbystatus_StatusInvalido_LanzaExcepcion() {

    assertThrows(
        ValidationException.class,
        () -> useCase.findbystatus("DESCONOCIDO")
    );
}

    @Test
    void findbystatus_StatusNull_LanzaExcepcion() {

    assertThrows(
        ValidationException.class,
        () -> useCase.findbystatus(null)
    );
    }

    @Test
    void findbystatus_StatusVacio_LanzaExcepcion() {

    assertThrows(
        ValidationException.class,
        () -> useCase.findbystatus("   ")
    );
}
}
