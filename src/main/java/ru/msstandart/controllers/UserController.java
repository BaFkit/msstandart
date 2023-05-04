package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.msstandart.dto.UserDto;
import ru.msstandart.dto.UserDtoForChangePass;
import ru.msstandart.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users/change/pass")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void changePassword(@RequestParam("id") Long userId, @RequestParam("password") String password) {
        userService.changePassword(userId, password);
    }
}
