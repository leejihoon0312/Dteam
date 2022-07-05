package com.example.Dteam.service;

import com.example.Dteam.domain.Seat;

import com.example.Dteam.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;
    public List<Seat> findSeats() {
        return seatRepository.findAll();
    }

    public Long createSeat(Seat seat){
        seatRepository.save(seat);
        return seat.getSeatNum();
    }

}
