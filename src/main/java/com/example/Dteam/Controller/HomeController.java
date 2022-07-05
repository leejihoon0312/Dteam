package com.example.Dteam.Controller;

import com.example.Dteam.domain.User;
import com.example.Dteam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {




    @RequestMapping("/")
    public String home(HttpServletRequest request,Model model){
        HttpSession session =request.getSession(false);

        if(session==null) {
            return "main/main";
        }

        User loginUser=(User)session.getAttribute(SessionConstants.LOGIN_USER);

        if(loginUser==null){
            return "main/main";
        }

        model.addAttribute("user",loginUser);

        return "main/main";
    }

    @RequestMapping("/admin")
    public String adminHome(HttpServletRequest request,Model model){

        return "main/mainPageAdmin";
    }

    @RequestMapping("/user")
    public String userHome(HttpServletRequest request,Model model){

        return "main/mainPage";
    }



}
