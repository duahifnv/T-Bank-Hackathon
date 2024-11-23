package org.hack.controller;

import org.hack.dto.LoginRequest;
import org.hack.dto.UserDto;
import org.hack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.loginValid(request)) {
            UserDto userDto = userService.findByLogin(request.getLogin());
            return ResponseEntity.ok().body(userDto);
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
