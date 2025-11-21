package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketsCacheByUsuarioUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;

@RestController
@RequestMapping("/api/v1/findcacheable_tickets")
@Tag(
    name = "Consultar tickets de Usuarios almacenados en Caché",
    description = "Este controlador se utiliza para tickets de Usuarios almacenados en Caché."
)
public class GetTicketsCacheByUsuarioController {
    
    @Autowired
    private GetTicketsCacheByUsuarioUseCase useCase;


    @Operation(summary = "Consultar Tickets en Caché por Usuario")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = """
        Este endpoint guardar temporalmente los tickets de un usuario en caché.  

        Comportamiento del caché:
        - La primera consulta realiza búsqueda en la base de datos y almacena los tickets en caché.
        - Las siguientes consultas con el mismo usuarioId recuperan la información **directamente desde la caché**, 
          mejorando el rendimiento.
        - Los datos se invalidan automáticamente cuando el ticket es creado, modificado o eliminado (si tienes invalidación).

        Importante:
        - Los datos en caché pueden estar desactualizados si otro sistema o proceso modifica los tickets directamente.
        - Usar la caché es ideal para consultas muy frecuentes sin cambios constantes.
        """,
        content = @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = TicketResponseDto.class))
        )
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                example = """
                {
                    "error": "Error interno del servidor",
                    "detalle": "Se produjo un error inesperado al Buscar el ticket en caché."
                }
                """
            )
        )
    )
})
        @GetMapping("/usuario/{usuarioId}")
        public ResponseEntity<?> getTickets(@PathVariable UUID usuarioId) {
            try {
            var tickets = useCase.obtenerTicketsPorUsuario(usuarioId)
                    .stream()
                    .map(TicketResponseDto::desdeDomain)
                    .toList();

            return ResponseEntity.ok(tickets);

            } catch (UsuarioNotFoundException ex) {

                return ResponseEntity.status(404).body(
                        java.util.Map.of(
                                "error", "Usuario no encontrado",
                                "detalle", ex.getMessage()
                        )
                );
            }
  }

}
