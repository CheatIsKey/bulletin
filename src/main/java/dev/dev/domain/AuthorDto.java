package dev.dev.domain;

import lombok.Data;

@Data
public class AuthorDto {

    private Long id;
    private String nickname;

    public static AuthorDto from(User user) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.id = user.getId();
        authorDto.nickname = user.getLoginId();

        return authorDto;
    }
}
