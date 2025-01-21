package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.RegistrationRequest;
import org.hack.entity.User;
import org.hack.mapper.UserMapper;
import org.hack.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Не найден пользователь с именем " + username
                        )
                );
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).toList()
        );
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public User findUserByParams(String username, String email) {
        if (username != null) {
            return findUserByUsername(username);
        }
        return findUserByEmail(email);
    }
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найден пользователь с id " + userId));
    }
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найден пользователь с именем " + username));
    }
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Не найден пользователь с почтой " + email));
    }
    @Transactional
    public User createNewUser(RegistrationRequest registrationRequest) {
        if (existsByUsername(registrationRequest.username()) ||
                existsByEmail(registrationRequest.email())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "В базе уже есть пользователь с такими данными");
        }
        return userRepository.save(
                userMapper.toUser(registrationRequest)
        );
    }
    @Transactional
    public void deleteUserById(Long userId) {
        if (!existsById(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден пользователь с id " + userId);
        }
        userRepository.deleteById(userId);
    }
    @Transactional
    public void deleteUserByUsername(String username) {
        if (!existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Не найден пользователь с именем " + username);
        }
        userRepository.deleteByUsername(username);
    }
    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
