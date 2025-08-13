package dev.dev.domain;

public class UserDto {

    private String name;

    private String phone;

    private String userId;

    private String password;

    public UserDto(String name, String phone, String userId, String password) {
        this.name = name;
        this.phone = phone;
        this.userId = userId;
        this.password = password;
    }
}
