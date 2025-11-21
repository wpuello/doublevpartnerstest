package tickets.doublevpartnets.tickets;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import tickets.doublevpartnets.tickets.Application.UseCases.Security.CreateUserUseCase;

@SpringBootApplication
@EnableCaching
public class TicketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsApplication.class, args);
	}

	@Bean
	public CommandLineRunner initAdmin(CreateUserUseCase createUser) {
		return args -> {
			if (createUser.exists("admin") == false) {
				createUser.create("admin", "admin123", "ADMIN");
				System.out.println("Usuario ADMIN creado por defecto");
			}
		};
	}
}
