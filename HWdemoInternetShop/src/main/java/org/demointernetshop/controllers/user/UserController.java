package org.demointernetshop.controllers.user;

import lombok.RequiredArgsConstructor;
import org.demointernetshop.dto.user.LoginRequestDto;
import org.demointernetshop.dto.user.UserDto;
import org.demointernetshop.dto.user.UserRegistrationDto;
import org.demointernetshop.dto.user.UserUpdateDto;
import org.demointernetshop.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(UserRegistrationDto request) {
        return userService.createUser(request);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(UserUpdateDto request, Integer userId) {
        return userService.updateUser(userId, request);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginRequestDto request) {
        UserDto userDto = userService.loginUser(request);
        return ResponseEntity.ok(userDto);
    }
}
