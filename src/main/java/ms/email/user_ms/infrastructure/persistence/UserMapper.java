package ms.email.user_ms.infrastructure.persistence;

import ms.email.user_ms.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);
}
