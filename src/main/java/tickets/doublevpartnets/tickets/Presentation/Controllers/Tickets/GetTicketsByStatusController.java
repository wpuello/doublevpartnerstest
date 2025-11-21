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
import jakarta.validation.ValidationException;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketsByStatusUseCase;
import tickets.doublevpartnets.tickets.Domain.Enums.TicketStatus;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;

@RestController
@RequestMapping("api/v1/findbystatus_tickets")
@Tag(
    name = "Consultar todos los Tickets por Status",
    description = "Este controlador se utiliza para Mostrar todos los Tickets por Status."
)
public class GetTicketsByStatusController {

    @Autowired
    private GetTicketsByStatusUseCase ticketbysstatus;


   @Operation(summary = "Consultar Tickets por Status (Abierto/Cerrado)")
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
   @GetMapping("/{status}")
    public ResponseEntity<?> listar(@PathVariable String status) {

        try {
            var lista = ticketbysstatus.findbystatus(status)
                    .stream()
                    .map(TicketResponseDto::desdeDomain)
                    .toList();

            return ResponseEntity.ok(lista);

        } catch (ValidationException ex) {
            return ResponseEntity.status(400).body(
                java.util.Map.of(
                    "error", "Status inválido",
                    "detalle", ex.getMessage()
                )
            );
        }
    }
}
