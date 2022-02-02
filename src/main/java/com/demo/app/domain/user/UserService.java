package com.demo.app.domain.user;

import com.demo.app.domain.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUser(Long userId) {
        var user = userRepository.getUser(userId);
        if (user == null) {
            throw new ObjectNotFoundException(String.format("User with id: %d not found", userId));
        }
        return user;
    }
}
