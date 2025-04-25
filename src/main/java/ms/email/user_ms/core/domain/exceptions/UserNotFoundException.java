package ms.email.user_ms.core.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id){
        super("User with ID " + id + " could't be found");
    }
}
