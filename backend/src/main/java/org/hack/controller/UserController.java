package org.hack.controller;

import org.hack.dto.UserDto;
import org.hack.service.UserService;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
            throws URISyntaxException {
        UserDto result = userService.save(userDto);
        return ResponseEntity.created(new URI("/users/" + result.getId()))
                .body(result);
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
