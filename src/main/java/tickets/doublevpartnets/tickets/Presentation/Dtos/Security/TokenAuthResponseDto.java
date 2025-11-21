package tickets.doublevpartnets.tickets.Presentation.Dtos.Security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenAuthResponseDto {
    private String token;
    private String type;
    private long expiresIn;
}
