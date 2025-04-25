package ms.email.user_ms.core.ports.in;

import ms.email.user_ms.core.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindUserUseCase {
    Optional<User> findUserById(UUID id);
    Optional<User> findUserByEmail(String email);
    List<User> findAllUsers();
}
