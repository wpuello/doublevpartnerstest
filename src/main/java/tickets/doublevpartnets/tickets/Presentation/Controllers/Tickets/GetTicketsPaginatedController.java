package tickets.doublevpartnets.tickets.Presentation.Controllers.Tickets;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Tickets.GetTicketsPaginatedUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;

@RestController
@RequestMapping("api/v1/findpaginated_tickets")
@Tag(
    name = "Consultar todos los Tickets Paginados",
    description = "Este controlador se utiliza para Mostrar todos los Tickets de manera Paginada."
)
public class GetTicketsPaginatedController {


    @Autowired
    private GetTicketsPaginatedUseCase ticketspaginados;
   

    @Operation(summary = "Tickets Paginados")
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
    @GetMapping("/{page}/{size}")   
    public List<TicketResponseDto> listapaginada(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {

            return ticketspaginados.encontrarpaginado(page,size)
              .stream().map(TicketResponseDto::desdeDomain).toList();

        }
    
}
