package com.esgi.velib_commenter_api.modules.users.exposition;

import com.esgi.velib_commenter_api.modules.users.application.CreateUser;
import com.esgi.velib_commenter_api.modules.users.domain.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
