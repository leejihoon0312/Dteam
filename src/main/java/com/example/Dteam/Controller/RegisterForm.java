package com.example.Dteam.Controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegisterForm {

    private Long totalTableNum;
    private String registerDate; //Date로 설정하면 템플릿에서  타입미스매치 오류나서 이렇게 설정했어요
}
