package com.esgi.velib_commenter_api.modules.authentification.domain;

public interface TokenService {
    Token generateToken(String email, String userId);

    String getUserId(Token token);
}
