package com.demo.app.application.user;

import com.demo.app.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping(value = "/api/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers().stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/api/users/{userId}")
    public UserDto getUser(@PathVariable Long userId) {
        var user = userService.getUser(userId);
        return userDtoMapper.toDto(user);
    }
}
