package org.hack.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешная авторизация"),
            @ApiResponse(responseCode = "401", description = "Пользователя нет в системе или неверный пароль"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.loginValid(request)) {
            UserDto userDto = userService.findByLogin(request.getLogin());
            return ResponseEntity.ok().body(userDto);
        }
        return new ResponseEntity<>("Пользователя нет в системе или неверный пароль",
                HttpStatus.UNAUTHORIZED);
    }
}
