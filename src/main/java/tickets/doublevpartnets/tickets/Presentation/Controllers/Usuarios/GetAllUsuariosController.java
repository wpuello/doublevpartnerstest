package tickets.doublevpartnets.tickets.Presentation.Controllers.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tickets.doublevpartnets.tickets.Application.UseCases.Usuarios.GetAllUsuariosUseCase;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Usuarios.UsuarioResponseDto;

@RestController
@RequestMapping("/api/v1/getall_usuarios")
@Tag(
    name = "Listado de Usuarios Creados",
    description = "Este controlador se utiliza para Mostrar los Usuarios que crean los tickets."
)
public class GetAllUsuariosController {
    

    @Autowired 
    private GetAllUsuariosUseCase listaUseCase;

    @Operation(summary = "Listar Usuarios")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Lista de Usuarios creados",
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
                    "detalle": "Se produjo un error inesperado al mostrar los Usuarios."
                }
                """
            )
        )
    )
})
    @GetMapping
        public List<UsuarioResponseDto> listausuarios() {
            return listaUseCase.findAll()
                    .stream()
                    .map(UsuarioResponseDto::desdeDomain)
                    .toList();
        }


}
