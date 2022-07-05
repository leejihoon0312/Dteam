package com.example.Dteam.service;

import com.example.Dteam.domain.Reservation;
import com.example.Dteam.domain.User;
import com.example.Dteam.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;



    @Test
    public void 예약_및_삭제(){

        //given
        Reservation reservation=new Reservation();
        User user = new User();
        user.setUserId("test");
        reservation.setUser(user);
        int count=0;

        //when
        Long reservation1 = reservationService.reservation(reservation);//예약 정보 저장
        reservationService.reservationDelete(reservation);//예약 삭제
        List<Reservation> allReservation = reservationRepository.findAllReservation();
        for (Reservation reservation2 : allReservation) {
            count+=1;  //삭제 했기 때문에 카운트는 올라가지 않는다
        }

        //then
        org.assertj.core.api.Assertions.assertThat(count).isZero();

    }

}