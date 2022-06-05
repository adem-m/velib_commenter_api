package com.esgi.velib_commenter_api.modules.users.domain;

import com.esgi.velib_commenter_api.modules.security.domain.SecurityService;
import com.esgi.velib_commenter_api.modules.users.application.CreateUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserService(UserRepository userRepository,
                       SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    public void createUser(CreateUser createUser) {
        log.info("Create user request for user {}", createUser.email());
        String encryptedPassword = securityService.encrypt(createUser.password());
        User user = new User(
                UUID.randomUUID().toString(),
                createUser.firstName(),
                createUser.lastName(),
                createUser.email(),
                encryptedPassword
        );
        userRepository.add(user);
    }
}
