package org.hack.controller;

import org.hack.dto.UserDto;
import org.hack.dto.WalletDto;
import org.hack.entity.User;
import org.hack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hack.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final WalletService walletService;
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto resultDto = userService.save(userDto);
        walletService.createWalletForUser(resultDto);
        return ResponseEntity.ok().body(resultDto);
    }
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(userService.save(userDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
