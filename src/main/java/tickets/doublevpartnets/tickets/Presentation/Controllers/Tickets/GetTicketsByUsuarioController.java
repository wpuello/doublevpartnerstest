package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketsByUsuarioUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;


@RestController
@RequestMapping("api/v1/findbyusuario_tickets")
@Tag(
    name = "Consultar todos los Tickets por Usuario",
    description = "Este controlador se utiliza para Mostrar todos los Tickets por Usuario."
)
public class GetTicketsByUsuarioController {
    
    @Autowired
    private GetTicketsByUsuarioUseCase usecase;

    @Operation(summary = "Consultar Tickets por Usuario")
     @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Ticket consultado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TicketResponseDto.class)
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
                    "detalle": "Se produjo un error inesperado al Buscar el ticket."
                }
                """
            )
        )
    )
})
    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> listar(@PathVariable UUID usuarioId) {

        try {

            var tickets = usecase.execute(usuarioId)
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

