package com.esgi.velib_commenter_api.modules.users.infrastructure;

import com.esgi.velib_commenter_api.modules.users.domain.User;
import com.esgi.velib_commenter_api.modules.users.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringDataUserRepository implements UserRepository {
    private final JpaUserRepository userRepository;

    public SpringDataUserRepository(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(User user) {
        userRepository.save(UserMapper.toEntity(user));
    }

    @Override
    public User findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user == null ? null : UserMapper.toDomain(user);
    }
}

@Repository
interface JpaUserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByEmail(String email);
}