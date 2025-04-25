package ms.email.user_ms.core.ports.out;

import ms.email.user_ms.core.domain.User;

import java.util.UUID;

public interface UserWriteRepository {
    User save(User user);
    User updateUser(UUID id, User user);
}
