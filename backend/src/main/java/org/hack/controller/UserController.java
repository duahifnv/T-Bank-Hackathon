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
    @GetMapping("{/id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @GetMapping("/{login}")
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(userService.findByLogin(login));
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
    @DeleteMapping("/{login}")
    public ResponseEntity<?> deleteUserByLogin(@PathVariable String login) {
        userService.deleteByLogin(login);
        return ResponseEntity.ok().build();
    }
}
