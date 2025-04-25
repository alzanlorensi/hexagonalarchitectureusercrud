package ms.email.user_ms.config;

import ms.email.user_ms.core.application.UserService;
import ms.email.user_ms.core.ports.out.UserDeleteRepository;
import ms.email.user_ms.core.ports.out.UserReadRepository;
import ms.email.user_ms.core.ports.out.UserWriteRepository;
import ms.email.user_ms.infrastructure.persistence.UserJPARepository;
import ms.email.user_ms.infrastructure.persistence.UserMapper;
import ms.email.user_ms.infrastructure.persistence.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public UserService userService(UserReadRepository userReadRepository, UserWriteRepository userWriteRepository, UserDeleteRepository userDeleteRepository){
        return new UserService(userReadRepository, userWriteRepository,userDeleteRepository);
    }

    @Bean
    public UserReadRepository userReadRepository(UserJPARepository jpaRepository, UserMapper mapper){
        return new UserRepositoryAdapter(jpaRepository,mapper);
    }

    @Bean
    public UserWriteRepository userWriteRepository(UserJPARepository jpaRepository, UserMapper mapper){
        return new UserRepositoryAdapter(jpaRepository,mapper);
    }

    @Bean
    public UserDeleteRepository userDeleteRepository(UserJPARepository jpaRepository, UserMapper mapper){
        return  new UserRepositoryAdapter(jpaRepository,mapper);
    }
}
