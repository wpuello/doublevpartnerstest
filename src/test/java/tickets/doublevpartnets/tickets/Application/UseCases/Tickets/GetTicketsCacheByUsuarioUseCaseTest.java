package tickets.doublevpartnets.tickets.Application.UseCases.Tickets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Tickets.ITicketRepository;
import tickets.doublevpartnets.tickets.Domain.Repositories.Interfaces.Usuarios.IUsuarioRepository;


public class GetTicketsCacheByUsuarioUseCaseTest {

    @Mock
    private ITicketRepository ticketRepository;

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private GetTicketsCacheByUsuarioUseCase useCase;

    @BeforeEach
    void setup() {
    MockitoAnnotations.openMocks(this);
    useCase = new GetTicketsCacheByUsuarioUseCase(ticketRepository, usuarioRepository);
    }

    @TestConfiguration
    static class CacheConfig {
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("ticketsPorUsuario");
        }
    }

    @Test
    void obtenerTickets_UsuarioExiste_RetornaLista() {

        UUID usuarioId = UUID.randomUUID();

        Usuario usuarioMock = Usuario.builder()
                .id(usuarioId)
                .nombres("Juan")
                .apellidos("Perez")
                .build();

        Ticket t1 = Ticket.builder().descripcion("Ticket A").usuarioId(usuarioId).build();
        Ticket t2 = Ticket.builder().descripcion("Ticket B").usuarioId(usuarioId).build();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioMock));
        when(ticketRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(t1, t2));

        var resultado = useCase.obtenerTicketsPorUsuario(usuarioId);

        assertEquals(2, resultado.size());
        verify(ticketRepository, times(1)).findByUsuarioId(usuarioId);
    }

    @Test
    void obtenerTickets_UsuarioNoExiste_LanzaExcepcion() {

        UUID usuarioId = UUID.randomUUID();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(
                UsuarioNotFoundException.class,
                () -> useCase.obtenerTicketsPorUsuario(usuarioId)
        );

        verify(ticketRepository, never()).findByUsuarioId(any());
    }

}
