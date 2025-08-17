package dev.dev.controller;

import dev.dev.domain.UserCreateRequest;
import dev.dev.domain.UserDto;
import dev.dev.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.findUsers().stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getNickname(), u.getPhone(), u.getLoginId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDto findUser(@PathVariable("id") Long id) {
        return userService.findOne(id);
    }

    @PostMapping("/register")
    public UserDto createUser(@RequestBody @Valid UserCreateRequest request) {
        return userService.create(request);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateRequest request) {
        UserDto updated =userService.update(
                id,
                request.getName(),
                request.getPhone(),
                request.getPassword()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Data
    private static class UserUpdateRequest {
        @Size(max = 20)
        private String name;

        @Size(max = 20)
        private String phone;

        @Size(max = 20)
        private String password;

    }
}
