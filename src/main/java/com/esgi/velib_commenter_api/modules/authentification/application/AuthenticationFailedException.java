package com.esgi.velib_commenter_api.modules.authentification.application;

public class AuthenticationFailedException extends IllegalArgumentException {
    public AuthenticationFailedException() {
        super("Authentication failed");
    }
}
