package ms.email.user_ms.infrastructure.persistence;

import jakarta.transaction.Transactional;
import ms.email.user_ms.core.domain.User;
import ms.email.user_ms.core.ports.out.UserDeleteRepository;
import ms.email.user_ms.core.ports.out.UserReadRepository;
import ms.email.user_ms.core.ports.out.UserWriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserReadRepository, UserWriteRepository, UserDeleteRepository {

    private final UserJPARepository userJPARepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(UserJPARepository userJPARepository, UserMapper mapper) {
        this.userJPARepository = userJPARepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        UserEntity savedUser = userJPARepository.save(entity);
        return mapper.toDomain(savedUser);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return userJPARepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<UserEntity> userEntity = userJPARepository.findByEmail(email);
        if (userEntity.isEmpty()) {
            return Optional.empty();
        }
        Optional<User> user = Optional.ofNullable(mapper.toDomain(userEntity.get()));
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> entities = userJPARepository.findAll();
        return entities.stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public User updateUser(UUID id, User user) {
        return userJPARepository.findById(id)
                .map(userFound -> {
                    userFound.setEmail(user.getEmail());
                    userFound.setName(user.getName());
                    UserEntity entity = userJPARepository.save(userFound);
                    return mapper.toDomain(entity);
                })
                .orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteById(UUID id) {
        return userJPARepository.findById(id)
                .map(
                        userEntity -> {
                            userJPARepository.deleteById(userEntity.getId());
                            return ResponseEntity.ok().body("User with id " + id + "was deleted.");
                        }
                ).orElse(null);
    }
}
