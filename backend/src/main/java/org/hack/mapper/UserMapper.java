package org.hack.mapper;

import org.hack.dto.request.RegistrationRequest;
import org.hack.dto.response.UserResponse;
import org.hack.entity.User;
import org.hack.service.RoleService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", imports = Collections.class)
public abstract class UserMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected RoleService roleService;
    public abstract UserResponse toUserResponse(User user);
    public abstract List<UserResponse> toUserResponse(List<User> users);
    @Mapping(target = "password", qualifiedByName = "getEncodedPassword")
    @Mapping(target = "roles", expression = "java(Collections.singletonList(roleService.getUserRole()))")
    public abstract User toUser(RegistrationRequest request);
    @Named("getEncodedPassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
