package org.hack.mapper;

import org.hack.dto.UserDto;
import org.hack.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToModel(UserDto userDto);
    UserDto modelToDto(User user);
    List<UserDto> toListDto(List<User> users);
}
