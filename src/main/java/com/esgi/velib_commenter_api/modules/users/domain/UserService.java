package com.esgi.velib_commenter_api.modules.users.domain;

import com.esgi.velib_commenter_api.modules.users.application.CreateUser;

public interface UserService {
    void createUser(CreateUser createUser);
}
