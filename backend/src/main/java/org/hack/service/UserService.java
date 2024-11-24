package org.hack.service;

import org.hack.dto.LoginRequest;
import org.hack.dto.UserDto;
import org.hack.entity.User;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto findByLogin(String login);
    UserDto save(UserDto userDto);
    void deleteById(Long id);
    void deleteByLogin(String login);
    boolean loginValid(LoginRequest request);
    boolean registerValid(UserDto userDto);
}
