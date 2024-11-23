package org.hack.mapper;

import org.hack.dto.UserDto;
import org.hack.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

// TODO: Адекватный маппинг
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
    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    List<User> toListModel(List<UserDto> users);
}
