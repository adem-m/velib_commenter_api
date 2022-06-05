package com.esgi.velib_commenter_api.modules.security.application;

import com.esgi.velib_commenter_api.modules.security.domain.SecurityService;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class DefaultSecurityService implements SecurityService {
    @Override
    public String encrypt(String password) {
        return Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
