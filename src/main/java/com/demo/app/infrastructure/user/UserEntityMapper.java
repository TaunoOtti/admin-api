package com.demo.app.infrastructure.user;

import com.demo.app.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity entity);
}
