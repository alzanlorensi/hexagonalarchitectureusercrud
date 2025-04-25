package ms.email.user_ms.core.ports.in;

import ms.email.user_ms.core.domain.User;

public interface CreateUserUseCase {
    User createUser(User user);
}
