package org.hack.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
            @ApiResponse(responseCode = "401", description = "Пользователь есть в системе"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        if (userService.registerValid(userDto)) {
            return ResponseEntity.ok().body(userService.save(userDto));
        }
        return new ResponseEntity<>("Пользователь уже в системе", HttpStatus.UNAUTHORIZED);
    }
}
