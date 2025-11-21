package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.DeleteTicketUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Tickets.TicketNotFoundException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.MessageResponseDto;

@RestController
@RequestMapping("/api/v1/delete_tickets")
@Tag(
    name = "Eliminación Principal de Tickets",
    description = "Este controlador se utiliza para Eliminar los tickets creados."
)
public class DeleteTicketController {


    @Autowired
    private DeleteTicketUseCase deletetickets;
    
    @Operation(summary = "Eliminar un Ticket")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "500",
        description = "Error interno del servidor",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(
                example = """
                {
                    "error": "Error interno del servidor",
                    "detalle": "Se produjo un error inesperado al eliminar el ticket."
                }
                """
            )
        )
    )
})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
         try {
            deletetickets.eliminar(id);
            return ResponseEntity.ok(new MessageResponseDto("Ticket eliminado correctamente"));

        } catch (TicketNotFoundException ex) {
            return ResponseEntity.status(404)
                    .body(
                            java.util.Map.of(
                                    "error", "Ticket no encontrado",
                                    "detalle", ex.getMessage()
                            )
                    );
        }
    }
}
