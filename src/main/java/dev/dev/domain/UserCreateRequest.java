package dev.dev.domain;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;
    private String nickname;
    private String phone;
    private String loginId;
    private String password;

    protected UserCreateRequest() {}

}
