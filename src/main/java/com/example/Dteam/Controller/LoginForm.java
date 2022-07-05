package com.example.Dteam.Controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "id는 필수 입니다")
    private String id;
    @NotEmpty(message = "password는 필수 입니다")
    private String password;
}
