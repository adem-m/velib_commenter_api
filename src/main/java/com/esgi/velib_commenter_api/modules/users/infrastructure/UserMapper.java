package com.esgi.velib_commenter_api.modules.users.infrastructure;

import com.esgi.velib_commenter_api.modules.users.domain.User;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        return new UserEntity(user.id(), user.firstName(), user.lastName(), user.email(), user.password());
    }

    public static User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword());
    }
}
