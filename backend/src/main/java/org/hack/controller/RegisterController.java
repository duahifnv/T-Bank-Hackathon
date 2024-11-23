package org.hack.controller;

import org.hack.dto.UserDto;
import org.hack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userDto.toString(), HttpStatus.UNAUTHORIZED);
        /*if (userService.registerValid(userDto)) {
            return ResponseEntity.ok().body(userService.save(userDto));
        }
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);*/
    }
}
