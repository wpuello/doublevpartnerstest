package tickets.doublevpartnets.tickets.Presentation.Controllers.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import tickets.doublevpartnets.tickets.Application.UseCases.Security.LoginUseCase;
import tickets.doublevpartnets.tickets.Domain.Exceptions.Security.InvalidCredentialsException;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Security.LoginRequestDto;
import tickets.doublevpartnets.tickets.Presentation.Dtos.Security.TokenAuthResponseDto;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
    name = "Login Principal",
    description = "Este controlador se utiliza para Autenticar el Usuario y Generarle un TOken JWT."
)
public class LoginController {

    @Autowired
    private LoginUseCase loginUseCase;


    @Operation(summary = "Autenticación")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "Usuario Inicia Sesion Exitosamente",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = TokenAuthResponseDto.class)
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
                    "detalle": "Se produjo un error inesperado al realizar el Login."
                }
                """
            )
        )
    )
})
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {

        try {
            TokenAuthResponseDto response = loginUseCase.login(dto.getUsername(), dto.getPassword());
            return ResponseEntity.ok(response);

        } catch (InvalidCredentialsException ex) {
            return ResponseEntity.status(401)
                    .body(java.util.Map.of("error", ex.getMessage()));
        }
    }
    
}
