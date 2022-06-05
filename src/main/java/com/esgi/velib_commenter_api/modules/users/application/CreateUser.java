package com.esgi.velib_commenter_api.modules.users.application;

public record CreateUser(String firstName, String lastName, String email, String password) {
}
