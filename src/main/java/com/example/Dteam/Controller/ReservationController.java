package com.example.Dteam.Controller;

import com.example.Dteam.domain.Reservation;
import com.example.Dteam.domain.Seat;

import com.example.Dteam.domain.User;
import com.example.Dteam.repository.ReservationRepository;
import com.example.Dteam.repository.ReservationsRepository;
import com.example.Dteam.repository.SeatRepository;
import com.example.Dteam.repository.UserRepository;
import com.example.Dteam.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final LoginService loginService;
    private final ReservationService reservationService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SeatService seatService;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationsRepository reservationsRepository;
    private final ReservationsService reservationsService;


    @GetMapping("/selectdate")  //우선 날짜먼저 선택합니다
    public String selectDateForm(Model model) {
        model.addAttribute("SelectDateForm", new SelectDateForm());
        return "reservation/reservationDateChoose";
    }

    @PostMapping("/selectdate")  //해당 날짜에 이용가능한 좌석을 넘겨줍니다
    public String selectDate(Model model, HttpServletRequest request, SelectDateForm selectDateForm) {


        List<Seat> seatList = new ArrayList<Seat>();
        String selectDate = selectDateForm.getSelectDate();

        List<Seat> seats = seatService.findSeats();
        for (Seat seat : seats) {
            if (seat.getRegisterDate().equals(selectDate) && (seat.isCanUse())) {
                seatList.add(seat);  //날짜가 일치하는 좌석만 넘겨준다 , 이용가능한 좌석을 넘겨준다
            }
        }

        model.addAttribute("date", selectDate);  //날짜값 넘기기
        model.addAttribute("seatList", seatList);  //좌석 넘기기
        return "reservation/reservationSecond";
    }


    @PostMapping("/reservation")
    public String reservation(HttpServletRequest request, @RequestParam("seatId") Long seatId) {


        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(SessionConstants.LOGIN_USER);
        List<Seat> all = seatRepository.findAll();

        Seat reservationSeat = null;
        for (Seat findSeat : all) {

            if (findSeat.getSeatId() == seatId) {

                reservationSeat = findSeat;  //목록중 날짜 좌석번호 일치하는 자리 선택
                reservationSeat.setCanUse(false);   //다른사람 이용불가


                break;

            }
        }


        Date currentTime = new Date();
        Reservation reservation = new Reservation();
        reservation.setUser(loginUser);
        reservation.setSeat(reservationSeat);
        reservation.setReservationDate(reservationSeat.getRegisterDate());

        reservationsService.reservation(reservation);

        return "redirect:/user";
    }



    @GetMapping("/reservationDelete")
    public String reservationDelete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(SessionConstants.LOGIN_USER);
        List<Reservation> byLogin = reservationRepository.findByLoginId(loginUser);
        Reservation deleteReservation = null;


        for (Reservation reservation : byLogin) {


            reservationsService.reservationDelete(reservation.getReservationId());
            reservation.getSeat().setCanUse(true);//다른사람 이용 가능

        }


        return "main/mainPage";
    }

    //예약 조회




    @GetMapping("reservationFind")
    public String reservationFind(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(SessionConstants.LOGIN_USER);
        List<Reservation> byLoginId = reservationRepository.findByLoginId(loginUser);
        Long seatNum;
        for (Reservation reservation : byLoginId) {


            seatNum = reservation.getSeat().getSeatNum();
            model.addAttribute("seatNum", seatNum);
            System.out.println(seatNum);

            break;
        }


        System.out.println("byLoginId = " + byLoginId);
        model.addAttribute("reservationList", byLoginId);

        return "reservation/reservationTable";
    }

    //관리자 예약 조회


    @GetMapping("reservationAdminFind")
    public String reservationFind(Model model) {


        List<Reservation> reservationList = reservationsService.reservationList();

        model.addAttribute("reservationList", reservationList);

        System.out.println("reservations = " + reservationList);


        return "reservation/reservationTable";
    }

    //예약 수정
    @GetMapping("/reservationUpdate")
    public String reservationUpdateDemo(HttpServletRequest request,Model model){
        model.addAttribute("SelectDateForm", new SelectDateForm());
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute(SessionConstants.LOGIN_USER);
        List<Reservation> byLogin = reservationRepository.findByLoginId(loginUser);
        Reservation deleteReservation = null;
        for (Reservation reservation : byLogin) {


            reservationsService.reservationDelete(reservation.getReservationId());
            reservation.getSeat().setCanUse(true);//다른사람 이용 가능


            return "reservation/reservationDateChoose";
        }

        return "reservation/reservationDateChoose";
    }

    @PostMapping("/reservationUpdate")
    public String reservationUpdate(Model model,SelectDateForm selectDateForm) {

        List<Seat> seatList = new ArrayList<Seat>();
        String selectDate = selectDateForm.getSelectDate();

        List<Seat> seats = seatService.findSeats();
        for (Seat seat : seats) {
            if (seat.getRegisterDate().equals(selectDate) && (seat.isCanUse())) {
                seatList.add(seat);  //날짜가 일치하는 좌석만 넘겨준다 , 이용가능한 좌석을 넘겨준다
            }
        }

        model.addAttribute("date", selectDate);  //날짜값 넘기기
        model.addAttribute("seatList", seatList);  //좌석 넘기기
        return "reservation/reservationSecond";
    }

    }
