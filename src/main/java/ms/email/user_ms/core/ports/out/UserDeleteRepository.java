package ms.email.user_ms.core.ports.out;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserDeleteRepository {
    ResponseEntity deleteById(UUID id);
}
