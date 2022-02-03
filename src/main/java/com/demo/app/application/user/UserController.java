package com.demo.app.application.user;

import com.demo.app.application.user.dto.UserDto;
import com.demo.app.application.user.dto.UserRequestDto;
import com.demo.app.domain.user.User;
import com.demo.app.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
        User user = userService.getUser(userId);
        return userDtoMapper.toDto(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/api/users")
    public UserDto createUser(@Valid @RequestBody UserRequestDto userDto) {
        User user = userDtoMapper.toUser(userDto);
        User createdUser = userService.saveUser(user);
        return userDtoMapper.toDto(createdUser);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/api/users/{userId}")
    public void updateUser(@PathVariable Long userId, @Valid @RequestBody UserRequestDto userDto) {
        User originalUser = userService.getUser(userId);
        userDtoMapper.mapFieldsToExistingUser(originalUser, userDto);
        userService.saveUser(originalUser);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/api/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);
    }
}
