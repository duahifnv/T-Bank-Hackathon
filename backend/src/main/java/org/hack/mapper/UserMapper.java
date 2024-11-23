package org.hack.mapper;

import org.hack.dto.UserDto;
import org.hack.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    User dtoToModel(UserDto userDto);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    UserDto modelToDto(User user);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    List<UserDto> toListDto(List<User> users);
}
