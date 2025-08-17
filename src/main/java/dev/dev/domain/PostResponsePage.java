package dev.dev.domain;

import java.time.LocalDateTime;

public record PostResponsePage(
        Long id,
        String title,
        String authorNickname,
        int commentCount,
        LocalDateTime createdAt
) {}
