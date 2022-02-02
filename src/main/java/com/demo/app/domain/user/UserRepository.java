package com.demo.app.domain.user;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUser(Long userId);

    User updateUser(User user);

    void deleteUser(User user);
}
