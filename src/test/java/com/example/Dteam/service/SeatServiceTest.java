package com.example.Dteam.service;

import com.example.Dteam.domain.Seat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class SeatServiceTest {

    @Autowired
    SeatService seatService;


    @Test
    public void 좌석생성_및_조회() {
        //given
        Seat seat = new Seat();
        seat.setSeatNum((long)10);
        seat.setRegisterDate("5/21");  //날짜지정
        seat.setCanUse(true);  //이용 가능한지
        Seat create=null;

        //when
        Long seatNum = seatService.createSeat(seat);
        List<Seat> seats = seatService.findSeats();
        for (Seat find : seats) {
            create=find;
        }

        //then
        org.assertj.core.api.Assertions.assertThat(create.getSeatNum()).isEqualTo(seatNum);


    }
}