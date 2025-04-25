package ms.email.user_ms.core.ports.in;

import ms.email.user_ms.core.domain.User;

import java.util.UUID;

public interface UpdateUserUseCase {
    User updateUser(UUID id, User user);
}
