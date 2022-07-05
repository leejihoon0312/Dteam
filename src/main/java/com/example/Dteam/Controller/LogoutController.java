package com.example.Dteam.Controller;

import com.example.Dteam.domain.User;
import com.example.Dteam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LogoutController {


    @GetMapping("logout")
    public String Logout(HttpServletRequest request){
        HttpSession session=request.getSession(false);
        if(session !=null){
            session.invalidate();
        }
        return "main/main";
    }
}
