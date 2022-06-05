package com.esgi.velib_commenter_api.modules.authentification.exposition;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotNull
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    public String password;
}
