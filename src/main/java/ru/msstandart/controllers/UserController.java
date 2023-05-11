package ru.msstandart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.msstandart.dto.ProfileDto;
import ru.msstandart.dto.UserDto;
import ru.msstandart.services.UserService;

import java.security.Principal;
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

    @PutMapping("/users/change/pass")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void changePassword(@RequestParam("id") Long userId, @RequestParam("password") String password) {
        userService.changePassword(userId, password);
    }

    @PutMapping("/users/change/firstName")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public void changeFirstName(Principal principal, @RequestParam("firstName") String firstName) {
        userService.changeFirstName(principal.getName(), firstName);
    }

    @GetMapping("/users/profile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ProfileDto getProfile(Principal principal) {
        return userService.getProfile(principal.getName());
    }

}
