package com.esgi.velib_commenter_api.modules.users.domain;

public interface UserRepository {
    void add(User user);
    User findByEmail(String email);
}
