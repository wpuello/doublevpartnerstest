package tickets.doublevpartnets.tickets.Presentation.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(
    name = "Pagina Principal",
    description = "Pagina Principal para Documentación."
)
@RestController
public class HomeController {


    @Operation(summary = "Pagina Principal")
    @GetMapping(value = "/", produces = "text/html")
    public String home() {

         return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <title>Microservicio de Tickets</title>
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #121212;
                            margin: 0;
                            padding: 0;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            height: 100vh;
                            color: #e0e0e0;
                        }
                        .container {
                            text-align: center;
                            background: #1e1e1e;
                            padding: 40px;
                            border-radius: 12px;
                            box-shadow: 0 4px 20px rgba(0,0,0,0.6);
                            border: 1px solid #333;
                        }
                        h1 {
                            color: #ffffff;
                            margin-bottom: 20px;
                        }
                        p {
                            color: #cccccc;
                            margin-bottom: 30px;
                            font-size: 18px;
                        }
                        .btn {
                            display: inline-block;
                            background-color: #007bff;
                            color: white;
                            padding: 12px 25px;
                            font-size: 16px;
                            border-radius: 8px;
                            text-decoration: none;
                            margin: 10px;
                            transition: 0.3s ease;
                        }
                        .btn:hover {
                            background-color: #0056b3;
                            transform: translateY(-2px);
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Microservicio de Tickets - Double V Partners</h1>
                        <p>Spring Boot con Java está funcionando correctamente</p>

                        <a class="btn" href="/README.md" target="_blank">Descargar Archivo README.md</a>
                        <a class="btn" href="/swagger-ui/index.html" target="_blank">Ir a la Documentación Swagger</a>
                        <a class="btn" href="http://localhost:9090/h2-console" target="_blank">Ir a Consola H2</a>
                    </div>
                </body>
                </html>
                """;
    }
}