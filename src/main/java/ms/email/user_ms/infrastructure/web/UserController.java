package ms.email.user_ms.infrastructure.web;

import ms.email.user_ms.core.domain.User;
import ms.email.user_ms.core.ports.in.CreateUserUseCase;
import ms.email.user_ms.core.ports.in.DeleteUserUseCase;
import ms.email.user_ms.core.ports.in.FindUserUseCase;
import ms.email.user_ms.core.ports.in.UpdateUserUseCase;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(FindUserUseCase findUserUseCase, UpdateUserUseCase updateUserUseCase, CreateUserUseCase createUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.findUserUseCase = findUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.createUserUseCase = createUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping
    public UserResponse saveUser(@RequestBody UserRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        User savedUser = createUserUseCase.createUser(user);
        return UserResponse.fromDomain(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) {
        return findUserUseCase.findUserById(id)
                .map(UserResponse::fromDomain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public UserResponse getUserByEmail(@RequestParam String email) {
        Optional<User> userByEmail = findUserUseCase.findUserByEmail(email);
        return UserResponse.fromDomain(userByEmail.get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> finAllUsers() {
        List<User> allUsers = findUserUseCase.findAllUsers();
        List<UserResponse> allUserEntity = allUsers.stream()
                .map(UserResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(allUserEntity);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestParam UUID id, @RequestBody UserRequest request){
        User user = new User();
        BeanUtils.copyProperties(request,user);
        UserResponse response = UserResponse.fromDomain(updateUserUseCase.updateUser(id, user));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id){
        return deleteUserUseCase.deleteUser(id);
    }
}
