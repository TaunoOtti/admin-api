package com.demo.app.application.user;

import com.demo.app.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto toDto(User user);
    User toUser(UserDto dto);

}
