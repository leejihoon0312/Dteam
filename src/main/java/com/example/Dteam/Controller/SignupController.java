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
public class SignupController {

    private final UserService userService;

    @RequestMapping("/signup")
    public String signHome(){return "index";}

    @RequestMapping("/users/new")
    public String createForm(Model model){
        model.addAttribute("userForm",new UserForm());

        return "signup/signUp";
    }

    @PostMapping("/users/new")
    public String create(UserForm userForm){
        User user = new User();
        user.setUserId(userForm.getUserId());
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());


        userService.join(user);

        return "redirect:/";
    }
}
