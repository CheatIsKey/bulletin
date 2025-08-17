package dev.dev.domain;

import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String nickname;
    private String phone;
    private String loginId;

    public UserDto(Long id, String name, String nickname, String phone, String loginId) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.loginId = loginId;
    }
}
