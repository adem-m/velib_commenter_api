package com.esgi.velib_commenter_api.modules.users.exposition;

import com.esgi.velib_commenter_api.modules.users.application.CreateUser;
import com.esgi.velib_commenter_api.modules.users.domain.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUser createUser = new CreateUser(
                request.firstName,
                request.lastName,
                request.email,
                request.password
        );
        userService.createUser(createUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Void> on() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
