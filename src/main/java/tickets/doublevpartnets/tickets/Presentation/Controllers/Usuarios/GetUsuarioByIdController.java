package tickets.doublevpartnets.tickets.Presentation.Controllers.Usuarios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import tickets.doublevpartnets.tickets.Application.UseCases.Usuarios.GetUsuarioByIdUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioResponseDto;

@RestController
@RequestMapping("/api/v1/getbyid_usuarios")
@Tag(
    name = "Consultar Usuario Creado por ID",
    description = "Este controlador se utiliza para Mostrar Usuario creado por ID."
)
public class GetUsuarioByIdController {


    @Autowired 
    private GetUsuarioByIdUseCase getbyIDUseCase;

    @Operation(summary = "Usuario por ID")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Usuarios por Identificador",
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
                    "detalle": "Se produjo un error inesperado al mostrar el Usuario."
                }
                """
            )
        )
    )
})
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {

    return getbyIDUseCase.find(id)
            .<ResponseEntity<?>>map(usuario ->
                    ResponseEntity.ok(UsuarioResponseDto.desdeDomain(usuario))
            )
            .orElseGet(() ->
                    ResponseEntity.status(404).body(
                            java.util.Map.of(
                                    "error", "Usuario no encontrado",
                                    "detalle", "El usuario con ID " + id + " no existe"
                            )
                    )
            );
    }
    
}
