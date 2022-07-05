package com.example.Dteam.service;



import com.example.Dteam.domain.User;
import com.example.Dteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;



    public User login(String loginId, String password) {
        List<User> byLoginId = userRepository.findByLoginId(loginId);
        for (User user : byLoginId) {
            String StudentId = user.getUserId();
            String password1 = user.getPassword();
            if (StudentId.equals(loginId))
            {
                if (password1.equals(password))
                {
                    return user;  //아이디 비번 일치시 해당학생 리턴
                }
            }

        }
        return null;    //없으면 널 리턴
    }

}
