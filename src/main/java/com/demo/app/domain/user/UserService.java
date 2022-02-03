package com.demo.app.domain.user;

import com.demo.app.domain.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public User getUser(Long userId) {
        var user = userRepository.getUser(userId);
        if (user == null) {
            throw new ObjectNotFoundException(String.format("User with id: %d not found", userId));
        }
        return user;
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.saveUser(user);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.saveUser(user);
    }

    @Transactional
    public void removeUser(Long userId) {
        getUser(userId);
        userRepository.deleteUserById(userId);
    }
}
