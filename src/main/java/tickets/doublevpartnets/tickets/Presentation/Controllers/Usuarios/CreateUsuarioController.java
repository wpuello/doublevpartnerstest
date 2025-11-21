package tickets.doublevpartnets.tickets.Presentation.Controllers.Usuarios;

import org.springframework.beans.factory.annotation.Autowired;
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
import tickets.doublevpartnets.tickets.Application.UseCases.Usuarios.CreateUsuarioUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Tickets.TicketResponseDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioRequestDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioResponseDto;

@RestController
@RequestMapping("/api/v1/create_usuarios")
@Tag(
    name = "Creación de Usuarios",
    description = "Este controlador se utiliza para crear los Usuario que crean los tickets."
)
public class CreateUsuarioController {

    @Autowired 
    private CreateUsuarioUseCase createUseCase;

    @Operation(summary = "Crear un Usuario")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Usuario creado exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = UsuarioResponseDto.class)
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
                    "detalle": "Se produjo un error inesperado al crear el Usuario."
                }
                """
            )
        )
    )
})
    @PostMapping
    public UsuarioResponseDto create(@Valid @RequestBody UsuarioRequestDto dto) {
        return UsuarioResponseDto.desdeDomain(
                createUseCase.create(dto.getNombres(), dto.getApellidos())
        );
    }
    
}
