package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.UpdateTicketUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketRequestDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketRequestUpdateDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;


@RestController
@RequestMapping("/api/v1/update_tickets")
@Tag(
    name = "Actualización Principal de Tickets",
    description = "Este controlador se utiliza para Actualizar los tickets creados."
)
public class UpdateTicketController {

    @Autowired
    private UpdateTicketUseCase updatetickets;

    @Operation(summary = "Actualizar un Ticket")
     @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Ticket Actualizado exitosamente",
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
                    "detalle": "Se produjo un error inesperado al actualizar el ticket."
                }
                """
            )
        )
    )
})
    @PutMapping("/{id}")
    public TicketResponseDto update(@PathVariable UUID id, @Valid  @RequestBody TicketRequestUpdateDto dto) {
        return TicketResponseDto.desdeDomain(
            updatetickets.actualizar(id, dto.getDescripcion(), dto.getStatus())
            );
    }
    
}
