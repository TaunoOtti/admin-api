package com.demo.app.domain.user;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUser(Long userId);

    User saveUser(User user);

    void deleteUserById(Long userId);
}
