package com.demo.app.application.user;

import com.demo.app.application.mapper.RequestMapperUtil;
import com.demo.app.application.user.dto.UserDto;
import com.demo.app.application.user.dto.UserRequestDto;
import com.demo.app.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        RequestMapperUtil.class
})
public interface UserDtoMapper {

    UserDto toDto(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdDtime", ignore = true)
    @Mapping(target = "modifiedDtime", ignore = true)
    User toUser(UserRequestDto dto);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdDtime", ignore = true)
    @Mapping(target = "modifiedDtime", ignore = true)
    void mapFieldsToExistingUser(@MappingTarget User originalUser, UserRequestDto dto);

}
