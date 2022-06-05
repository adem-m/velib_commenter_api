package com.esgi.velib_commenter_api.modules.authentification.exposition;

import com.esgi.velib_commenter_api.modules.authentification.application.AuthenticationFailedException;
import com.esgi.velib_commenter_api.modules.authentification.application.Login;
import com.esgi.velib_commenter_api.modules.authentification.domain.AuthService;
import com.esgi.velib_commenter_api.modules.authentification.domain.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        Token token = authService.login(new Login(request.email, request.password));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new LoginResponse(token.value()));
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<Map<String, String>> on(AuthenticationFailedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }
}
