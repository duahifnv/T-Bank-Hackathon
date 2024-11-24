package org.hack.service.impl;

import org.hack.dto.LoginRequest;
import org.hack.dto.UserDto;
import org.hack.entity.User;
import org.hack.mapper.UserMapper;
import org.hack.repository.UserRepository;
import org.hack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserDto> findAll() {
        return userMapper.toListDto(userRepository.findAll());
    }
    @Override
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "User with id: " + id + " not found"));
        return userMapper.modelToDto(user);
    }
    @Override
    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException(
                "User with login: " + login + " not found"));
        return userMapper.modelToDto(user);
    }
    @Override
    public UserDto save(UserDto userDto) {
        return userMapper.modelToDto(
                userRepository.save(userMapper.dtoToModel(userDto))
        );
    }
    @Override
    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "User with id: " + id + " not found"));
        userRepository.deleteById(id);
    }
    @Override
    public void deleteByLogin(String login) {
        userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException(
                        "User with login: " + login + " not found"));
        userRepository.deleteByLogin(login);
    }
    @Override
    public boolean loginValid(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByLogin(request.getLogin());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // TODO: BCrypt encoding
            return user.getPassword().equals(request.getPassword());
        }
        return false;
    }
    @Override
    public boolean registerValid(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByLogin(userDto.getLogin());
        return userOptional.isEmpty();
    }
}
