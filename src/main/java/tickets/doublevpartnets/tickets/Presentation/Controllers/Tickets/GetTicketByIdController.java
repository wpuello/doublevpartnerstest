package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketByIdUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;

@RestController
@RequestMapping("/api/v1/findbyid_tickets")
@Tag(
    name = "Consultar Tickets por Id",
    description = "Este controlador se utiliza para Consultar un Ticket por ID."
)
public class GetTicketByIdController {

    @Autowired
    private GetTicketByIdUseCase obtenerticketbyid;


    @Operation(summary = "Consultar Tickets por Id")
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
    @GetMapping("/{id}")
    public TicketResponseDto obtenerporid(@PathVariable UUID id) {
        return obtenerticketbyid.encontrarPorID(id)
                .map(TicketResponseDto::desdeDomain)
                .orElseThrow(() -> new RuntimeException("Ticket no se encontró"));
    }
    
}
