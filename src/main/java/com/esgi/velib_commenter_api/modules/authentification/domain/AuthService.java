package com.esgi.velib_commenter_api.modules.authentification.domain;

import com.esgi.velib_commenter_api.modules.authentification.application.AuthenticationFailedException;
import com.esgi.velib_commenter_api.modules.authentification.application.Login;
import com.esgi.velib_commenter_api.modules.security.domain.SecurityService;
import com.esgi.velib_commenter_api.modules.users.domain.User;
import com.esgi.velib_commenter_api.modules.users.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final TokenService tokenService;

    public AuthService(UserRepository userRepository, SecurityService securityService, TokenService tokenService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.tokenService = tokenService;
    }

    public Token login(Login login) {
        log.info("Login request for user {}", login.email());
        User user = userRepository.findByEmail(login.email());
        if (user == null) {
            log.error("User not found for email {}", login.email());
            throw new AuthenticationFailedException();
        }
        String encryptedPassword = securityService.encrypt(login.password());
        if (!encryptedPassword.equals(user.password())) {
            log.error("Password mismatch for user {}", login.email());
            throw new AuthenticationFailedException();
        }
        return tokenService.generateToken(user.id());
    }
}
