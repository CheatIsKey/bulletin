package dev.dev.controller;

import dev.dev.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/forget-id")
    public ResponseEntity<FindIdResponse> findLoginId(@Valid @RequestBody FindIdRequest request) {
        String masked = userService.findLoginIdByNameAndPhone(request.getName(), request.getPhone());
        return ResponseEntity.ok(new FindIdResponse(masked));
    }

    private record FindIdResponse(String maskedLoginId) {}

    @Data
    private static class FindIdRequest {

        @NotBlank
        @Size(max = 20)
        private String name;

        @NotBlank
        @Size(max = 20)
        private String phone;
    }
}
