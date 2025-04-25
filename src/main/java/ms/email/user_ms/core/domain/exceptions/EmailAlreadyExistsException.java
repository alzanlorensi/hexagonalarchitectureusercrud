package ms.email.user_ms.core.domain.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email){
        super ("Email "+ email + " already in use");
    }
}
