package ms.email.user_ms.core.ports.in;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface DeleteUserUseCase {
    ResponseEntity deleteUser(UUID id);
}
