package com.demo.app.domain.user;

import com.demo.app.domain.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldGetAllUsers() {
        var users = List.of(new User());
        when(userRepository.getAllUsers()).thenReturn(users);

        var list = userService.getAllUsers();

        verify(userRepository).getAllUsers();
        assertEquals(users, list);
    }

    @Test
    void shouldGetUser() {
        var newUser = new User();
        when(userRepository.getUser(1L)).thenReturn(newUser);

        var user = userService.getUser(1L);

        verify(userRepository).getUser(1L);
        assertEquals(newUser, user);
    }

    @Test
    void shouldGetUser_ThrowsObjectNotFound() {
        when(userRepository.getUser(1L)).thenReturn(null);

        var exception =
                assertThrows(ObjectNotFoundException.class, () -> userService.getUser(1L));

        assertEquals("User with id: 1 not found", exception.getMessage());
    }

    @Test
    void shouldSaveUser() {
        var savedUser = new User();
        when(userRepository.saveUser(any(User.class))).thenReturn(savedUser);
        var modifiedUser = new User();

        var response = userService.saveUser(modifiedUser);

        verify(userRepository).saveUser(modifiedUser);
        assertEquals(savedUser, response);
    }

    @Test
    void shouldRemoveUserById() {
        when(userRepository.getUser(1L)).thenReturn(new User());

        userService.removeUser(1L);

        verify(userRepository).deleteUserById(1L);
    }
}