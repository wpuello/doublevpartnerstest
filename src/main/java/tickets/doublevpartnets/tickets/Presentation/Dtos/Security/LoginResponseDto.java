package tickets.doublevpartnets.tickets.Presentation.Dtos.Security;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;
    private String role;
}
