package com.demo.app.infrastructure.user;

import com.demo.app.domain.user.User;
import com.demo.app.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public List<User> getAllUsers() {
        return userJpaRepository.findAll().stream()
                .map(userEntityMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(Long userId) {
        return userJpaRepository.findById(userId)
                .map(userEntityMapper::toUser)
                .orElse(null);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
