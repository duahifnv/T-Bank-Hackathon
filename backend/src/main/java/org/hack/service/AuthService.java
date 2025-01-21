package org.hack.service;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.AuthRequest;
import org.hack.dto.request.RegistrationRequest;
import org.hack.dto.response.JwtTokenResponse;
import org.hack.entity.User;
import org.hack.utils.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    public JwtTokenResponse createAuthToken(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.username(),
                            authRequest.password()
                    )
            );
            String generatedToken = jwtTokenUtils.generateToken(
                    userService.loadUserByUsername(authRequest.username())
            );
            return new JwtTokenResponse(generatedToken);
        }
        catch (BadCredentialsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Неверное имя пользователя или пароль");
        }
    }
    public User createNewUser(RegistrationRequest registrationRequest) {
        if (!registrationRequest.password().equals(registrationRequest.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Пароли не совпадают");
        }
        return userService.createNewUser(registrationRequest);
    }
}
