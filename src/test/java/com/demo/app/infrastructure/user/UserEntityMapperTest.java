package com.demo.app.infrastructure.user;

import com.demo.app.domain.user.UserTestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityMapperTest {
    
    private final UserEntityMapper userEntityMapper = new UserEntityMapperImpl(); 

    @Test
    void shouldMapDomainUserToEntity() {
        var user = UserTestUtil.createUser();
        var entity = userEntityMapper.toEntity(user);

        assertEquals(user.getUserId(), entity.getUserId());
        assertEquals(user.getFirstName(), entity.getFirstName());
        assertEquals(user.getLastName(), entity.getLastName());
        assertEquals(user.getAddress(), entity.getAddress());
        assertEquals(user.getEmail(), entity.getEmail());
        assertEquals(user.getBirthDate(), entity.getBirthDate());
        assertEquals(user.getPhone(), entity.getPhone());
        assertEquals(user.getCreatedDtime(), entity.getCreatedDtime());
        assertEquals(user.getModifiedDtime(), entity.getModifiedDtime());
    }

    @Test
    void shouldMapEntityToDomainUser() {
        var entity = createEntity();
        var user = userEntityMapper.toUser(entity);

        assertEquals(entity.getUserId(), user.getUserId());
        assertEquals(entity.getFirstName(), user.getFirstName());
        assertEquals(entity.getLastName(), user.getLastName());
        assertEquals(entity.getAddress(), user.getAddress());
        assertEquals(entity.getEmail(), user.getEmail());
        assertEquals(entity.getBirthDate(), user.getBirthDate());
        assertEquals(entity.getPhone(), user.getPhone());
        assertEquals(entity.getCreatedDtime(), user.getCreatedDtime());
        assertEquals(entity.getModifiedDtime(), user.getModifiedDtime());
    }

    private UserEntity createEntity() {
        UserEntity entity = new UserEntity();
        entity.setUserId(2L);
        entity.setFirstName("firstname1");
        entity.setLastName("lastname1");
        entity.setAddress("address1");
        entity.setEmail("email1@email.com");
        entity.setPhone("33322122");
        entity.setBirthDate(LocalDate.of(1990, 1, 1));
        entity.setCreatedDtime(LocalDateTime.now());
        entity.setModifiedDtime(LocalDateTime.now());
        return entity;
    }
}