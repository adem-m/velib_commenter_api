package com.esgi.velib_commenter_api.modules.users.application;

import com.esgi.velib_commenter_api.modules.security.domain.SecurityService;
import com.esgi.velib_commenter_api.modules.users.domain.User;
import com.esgi.velib_commenter_api.modules.users.domain.UserRepository;
import com.esgi.velib_commenter_api.modules.users.domain.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;

    public DefaultUserService(UserRepository userRepository,
                              SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public void createUser(CreateUser createUser) {
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
