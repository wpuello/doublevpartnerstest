package tickets.doublevpartnets.tickets.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(
    security = @SecurityRequirement(name = "bearerAuth")
)
public class OpenApiConfig {

    @Bean
    public OpenAPI baseOpenAPI() {
       return new OpenAPI()
                .info(new Info()
                        .title("Microservicio para Tickets - Para Double V Partners")
                        .version("1.0.0")
                        .description("Documentación de la API de Tickets By William Puello Elles"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
    
}
