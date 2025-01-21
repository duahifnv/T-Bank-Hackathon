package org.hack.controller.admin;

import lombok.RequiredArgsConstructor;
import org.hack.mapper.UserMapper;
import org.hack.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping()
    public ResponseEntity<?> getAllUsersByParams(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email) {
        if (username != null || email != null) {
            return ResponseEntity.ok(
                    userMapper.toUserResponse(
                            userService.findUserByParams(username, email)
                    )
            );
        }
        return ResponseEntity.ok(
                userMapper.toUserResponse(userService.findAllUsers())
        );
    }
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(
                userMapper.toUserResponse(userService.findUserById(userId))
        );
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
