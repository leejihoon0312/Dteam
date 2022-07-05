package com.example.Dteam.Controller;

import com.example.Dteam.repository.ReservationRepository;
import com.example.Dteam.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReservationModify {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;


}

//예약 조회

//예약 삭제

//예약 변경
