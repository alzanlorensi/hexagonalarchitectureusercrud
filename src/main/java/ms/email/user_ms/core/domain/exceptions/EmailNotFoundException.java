package ms.email.user_ms.core.domain.exceptions;

public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException(String email){
        super("Email  " + email + " could't be found");
    }
}
