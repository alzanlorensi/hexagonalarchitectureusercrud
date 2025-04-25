package ms.email.user_ms.core.ports.out;

import ms.email.user_ms.core.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserReadRepository {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();
}
