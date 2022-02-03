package com.demo.app.infrastructure.user;

import com.demo.app.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Mock
    private UserJpaRepository userJpaRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Test
    void shouldGetAllUsers() {
        when(userJpaRepository.findAll()).thenReturn(List.of(new UserEntity()));
        userRepository.getAllUsers();
        verify(userJpaRepository).findAll();
        verify(userEntityMapper).toUser(any(UserEntity.class));
    }

    @Test
    void getUser() {
        when(userJpaRepository.findById(1L)).thenReturn(Optional.of(new UserEntity()));
        userRepository.getUser(1L);
        verify(userJpaRepository).findById(1L);
        verify(userEntityMapper).toUser(any(UserEntity.class));
    }

    @Test
    void saveUser() {
        when(userEntityMapper.toEntity(any(User.class))).thenReturn(new UserEntity());
        when(userJpaRepository.save(any(UserEntity.class))).thenReturn(new UserEntity());
        userRepository.saveUser(new User());
        verify(userEntityMapper).toEntity(any(User.class));
        verify(userJpaRepository).save(any(UserEntity.class));
        verify(userEntityMapper).toUser(any(UserEntity.class));
    }

    @Test
    void deleteUserById() {
        userRepository.deleteUserById(1L);
        verify(userJpaRepository).deleteById(1L);
    }
}