package com.esgi.velib_commenter_api.modules.authentification.domain;

final public class TokenUtils {
    private static final String TOKEN_PREFIX = "Bearer";

    public static String getTokenValue(Token token) {
        return token.value().substring(TOKEN_PREFIX.length());
    }
}
