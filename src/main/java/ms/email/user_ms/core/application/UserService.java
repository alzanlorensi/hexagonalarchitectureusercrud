package ms.email.user_ms.core.application;

import ms.email.user_ms.core.domain.User;
import ms.email.user_ms.core.domain.exceptions.EmailAlreadyExistsException;
import ms.email.user_ms.core.domain.exceptions.EmailNotFoundException;
import ms.email.user_ms.core.domain.exceptions.UserNotFoundException;
import ms.email.user_ms.core.ports.in.CreateUserUseCase;
import ms.email.user_ms.core.ports.in.DeleteUserUseCase;
import ms.email.user_ms.core.ports.in.FindUserUseCase;
import ms.email.user_ms.core.ports.in.UpdateUserUseCase;
import ms.email.user_ms.core.ports.out.UserDeleteRepository;
import ms.email.user_ms.core.ports.out.UserReadRepository;
import ms.email.user_ms.core.ports.out.UserWriteRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements FindUserUseCase, UpdateUserUseCase, CreateUserUseCase, DeleteUserUseCase {
    private final UserReadRepository userReadRepository;
    private final UserWriteRepository userWriteRepository;
    private final UserDeleteRepository userDeleteRepository;

    public UserService(UserReadRepository userReadRepository, UserWriteRepository userWriteRepository, UserDeleteRepository userDeleteRepository) {
        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
        this.userDeleteRepository = userDeleteRepository;
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return Optional.ofNullable(userReadRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userReadRepository.findUserByEmail(email).orElseThrow(() -> new EmailNotFoundException(email)));
    }

    @Override
    public User createUser(User user) {
        if(userReadRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        return userWriteRepository.save(user);
    }

    @Override
    public User updateUser(UUID id, User user) {
        return userWriteRepository.updateUser(id, user);
    }

    @Override
    public List<User> findAllUsers() {
        return userReadRepository.findAllUsers();
    }

    @Override
    public ResponseEntity deleteUser(UUID id) {
        return userDeleteRepository.deleteById(id);
    }
}
