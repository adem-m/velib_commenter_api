package com.esgi.velib_commenter_api.modules.users.exposition;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateUserRequest {
    @NotNull
    @NotBlank
    public String firstName;

    @NotNull
    @NotBlank
    public String lastName;

    @NotNull
    @NotBlank
    @Email
    public String email;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^.{8,}$")
    public String password;
}
