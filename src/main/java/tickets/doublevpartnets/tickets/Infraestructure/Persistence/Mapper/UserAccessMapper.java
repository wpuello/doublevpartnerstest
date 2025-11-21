package tickets.doublevpartnets.tickets.Infraestructure.Persistence.Mapper;

import tickets.doublevpartnets.tickets.Domain.Model.Security.UsersAccess;
import tickets.doublevpartnets.tickets.Infraestructure.Persistence.Entity.Security.UsersAccessEntity;

public class UserAccessMapper {

    public static UsersAccessEntity toEntity(UsersAccess domain) {
        return UsersAccessEntity.builder()
                .id(domain.getId())
                .username(domain.getUsername())
                .password(domain.getPassword())
                .role(domain.getRole())
                .build();
    }

    public static UsersAccess toDomain(UsersAccessEntity entity) {
        return UsersAccess.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role(entity.getRole())
                .build();
    }
    
}
