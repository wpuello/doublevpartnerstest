package tickets.doublevpartnets.tickets.Presentation.Controllers.Usuarios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import tickets.doublevpartnets.tickets.Application.UseCases.Usuarios.UpdateUsuarioUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Usuarios.UsuarioNotFoundException;
import tickets.doublevpartnets.tickets.Domain.Model.Usuarios.Usuario;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioRequestDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioResponseDto;

@RestController
@RequestMapping("/api/v1/update_usuarios")
@Tag(
    name = "Actualizacion de Usuarios",
    description = "Este controlador se utiliza para Actualizar los Usuario que crean los tickets."
)
public class UpdateUsuarioController {

    @Autowired 
    private UpdateUsuarioUseCase updateUseCase;

    @Operation(summary = "Crear un Usuario")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Usuario actualizado exitosamente",
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
                    "detalle": "Se produjo un error inesperado al actualizar el Usuario."
                }
                """
            )
        )
    )
})
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @Valid @RequestBody UsuarioRequestDto dto) {
        try {

            Usuario actualizado = updateUseCase.update(id, dto.getNombres(), dto.getApellidos());
            return ResponseEntity.ok(actualizado);

        } catch (UsuarioNotFoundException ex) {

            return ResponseEntity.status(404)
                    .body(
                        java.util.Map.of(
                            "error", "Usuario no encontrado",
                            "detalle", ex.getMessage()
                        )
                    );
        }
    }
}