package ms.email.user_ms.infrastructure.web;

import lombok.Getter;
import lombok.Setter;
import ms.email.user_ms.core.domain.User;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private UUID id;
    private String name;
    private String email;

    public static UserResponse fromDomain(User user) {
        UserResponse response=  new UserResponse();
        BeanUtils.copyProperties(user,response);
        return response;
    }

}
