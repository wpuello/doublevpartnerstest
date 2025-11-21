package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketsByStatusAndUsuarioUseCase;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Domain.Exceptions.ValidationException;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;

@RestController
@RequestMapping("api/v1/find_tickets")
@Tag(
    name = "Consultar Tickets por Status y Usuario",
    description = "Este controlador se utiliza para Consultar un Ticket por Status y Usuario."
)
public class GetTicketsByStatusAndUsuarioController {
    
    @Autowired
    private GetTicketsByStatusAndUsuarioUseCase usecase;


    @Operation(summary = "Consultar Tickets por Status y Usuario.")
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
    @GetMapping
public ResponseEntity<?> listar(
        @RequestParam String status,
        @RequestParam UUID usuarioId
) {

    try {

        var tickets = usecase.findSU(status, usuarioId)
                .stream()
                .map(TicketResponseDto::desdeDomain)
                .toList();

        return ResponseEntity.ok(tickets);

    } catch (ValidationException e) {

        return ResponseEntity.badRequest().body(
                java.util.Map.of(
                        "error", "Validación fallida",
                        "detalle", e.getMessage()
                )
        );

    } catch (UsuarioNotFoundException e) {

        return ResponseEntity.status(404).body(
                java.util.Map.of(
                        "error", "Usuario no encontrado",
                        "detalle", e.getMessage()
                )
        );
    }
  }   
}
