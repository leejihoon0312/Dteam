package com.example.Dteam.service;

import com.example.Dteam.domain.User;
import com.example.Dteam.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class LoginServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired UserService userService;
    @Autowired LoginService loginService;

    @Test
    public void 회원가입_및_로그인(){
        //given
        User user=new User();
        user.setUserId("201811612");
        user.setPassword("201811612");

        //when
        String saveId = userService.join(user);
        User loginenable = loginService.login("201811612", "201811612");
        User logindisable = loginService.login("NotEnrolledUser", "NotEnrolledUser");


        //then
        Assertions.assertEquals(user,loginenable);
        Assertions.assertEquals(logindisable,null);

    }

}