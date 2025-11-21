package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.CreateTicketUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketRequestDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;


@RestController
@RequestMapping("/api/v1/crear_tickets")
@Tag(
    name = "Creación Principal de Tickets",
    description = "Este controlador se utiliza para crear los tickets."
)
public class CreateTicketController {

    @Autowired
    private CreateTicketUseCase createticket;

   
    @Operation(summary = "Crear un Ticket")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Ticket creado exitosamente",
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
                    "detalle": "Se produjo un error inesperado al crear el ticket."
                }
                """
            )
        )
    )
})
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TicketRequestDto dto) {

        try {
            var ticket = createticket.guardar(dto.getDescripcion(), dto.getUsuarioId());
            return ResponseEntity.ok(TicketResponseDto.desdeDomain(ticket));

        } catch (UsuarioNotFoundException ex) {
            return ResponseEntity.status(404).body(
                    java.util.Map.of(
                            "error", "Usuario no encontrado",
                            "detalle", ex.getMessage()
                    )
            );
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(
                    java.util.Map.of(
                            "error", "Error interno en el servidor",
                            "detalle", ex.getMessage()
                    )
            );
        }
    }
}
