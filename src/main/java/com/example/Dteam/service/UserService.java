package com.example.Dteam.service;

import com.example.Dteam.domain.User;
import com.example.Dteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public String join(User user){
        userRepository.save(user);
        return user.getUserId();
    }


    //예약 조회



}
