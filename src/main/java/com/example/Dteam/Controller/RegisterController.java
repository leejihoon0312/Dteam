package com.example.Dteam.Controller;

import com.example.Dteam.domain.Seat;


import com.example.Dteam.domain.User;
import com.example.Dteam.service.SeatService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Controller
public class RegisterController {

    private  final SeatService seatService;

    @GetMapping("/register")
    public String register(HttpServletRequest request, Model model) {
        model.addAttribute("registerform",new RegisterForm());  //관리자가 테이블 등록을 시도할떄
        return "register/makeTable";

    }

    @PostMapping("/register")
    public String create(HttpServletRequest request, Model model, RegisterForm registerForm) {
        for (int i=1; i<registerForm.getTotalTableNum()+1; i++ ){
            for (int j=12;j<=20;j+=2){
                Seat seat = new Seat();
                seat.setSeatNum((long)i);      //운영할 테이블 수 만큼 자리생성
                seat.setRegisterDate(registerForm.getRegisterDate());  //날짜지정
                seat.setCanUse(true);  //이용 가능한지
                seat.setTime((long)j); //시간대 생성  12~24시 2시간 단위
                seatService.createSeat(seat);  // 자리 생성하기
            }
        }
        return "redirect:/admin";

    }
}
