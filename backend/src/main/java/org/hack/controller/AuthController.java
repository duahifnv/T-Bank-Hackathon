package org.hack.controller;

import lombok.RequiredArgsConstructor;
import org.hack.dto.request.AuthRequest;
import org.hack.dto.request.RegistrationRequest;
import org.hack.mapper.UserMapper;
import org.hack.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authentication")
public class AuthController {
    private final AuthService authService;
    private final UserMapper userMapper;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.createAuthToken(authRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toUserResponse(
                        authService.createNewUser(registrationRequest)
                ));
    }
}
