package com.demo.app.application.user;

import com.demo.app.application.mapper.RequestMapperUtil;
import com.demo.app.application.user.dto.UserDto;
import com.demo.app.application.user.dto.UserRequestDto;
import com.demo.app.domain.user.User;
import com.demo.app.domain.user.UserTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    private UserDtoMapperImpl userDtoMapper;

    @Spy
    private RequestMapperUtil requestMapperUtil;

    @Test
    void shouldMapUsertoUserDto() {
        User user = UserTestUtil.createUser();
        UserDto dto = userDtoMapper.toDto(user);
        assertEquals(user.getUserId(), dto.getUserId());
        assertEquals(user.getFirstName(), dto.getFirstName());
        assertEquals(user.getLastName(), dto.getLastName());
        assertEquals(user.getAddress(), dto.getAddress());
        assertEquals(user.getEmail(), dto.getEmail());
        assertEquals(user.getBirthDate(), dto.getBirthDate());
        assertEquals(user.getCreatedDtime(), dto.getCreatedDtime());
        assertEquals(user.getModifiedDtime(), dto.getModifiedDtime());
    }

    @Test
    void shouldMapFieldsToExistingUser() {
        User user = UserTestUtil.createUser();
        UserRequestDto dto = createUserRequest();
        userDtoMapper.mapFieldsToExistingUser(user, dto);
        assertEquals(dto.getFirstName(), dto.getFirstName());
        assertEquals(dto.getLastName(), dto.getLastName());
        assertEquals(dto.getAddress(), dto.getAddress());
        assertEquals(dto.getEmail(), dto.getEmail());
        assertEquals(dto.getBirthDate(), dto.getBirthDate());
        assertEquals(1L, user.getUserId());
        assertNotNull(user.getCreatedDtime());
        assertNotNull(user.getModifiedDtime());

    }

    @Test
    void shouldMapUserDtoToUser() {
        UserRequestDto dto = createUserRequest();
        User user = userDtoMapper.toUser(dto);
        assertEquals(dto.getFirstName(), dto.getFirstName());
        assertEquals(dto.getLastName(), dto.getLastName());
        assertEquals(dto.getAddress(), dto.getAddress());
        assertEquals(dto.getEmail(), dto.getEmail());
        assertEquals(dto.getBirthDate(), dto.getBirthDate());
        assertNull(user.getUserId());
        assertNull(user.getCreatedDtime());
        assertNull(user.getModifiedDtime());
    }

    private UserRequestDto createUserRequest() {
        UserRequestDto request = new UserRequestDto();
        request.setFirstName("firstname1");
        request.setLastName("lastname1");
        request.setAddress("address1");
        request.setEmail("email1@email.com");
        request.setPhone("33322122");
        request.setBirthDate(LocalDate.of(1990, 1, 1));
        return request;
    }
}