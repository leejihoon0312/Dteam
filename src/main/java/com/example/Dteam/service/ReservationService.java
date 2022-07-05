package com.example.Dteam.service;

import com.example.Dteam.domain.Reservation;
import com.example.Dteam.domain.User;
import com.example.Dteam.repository.ReservationRepository;
import com.example.Dteam.repository.SeatRepository;
import com.example.Dteam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ReservationRepository reservationRepository;

    public Long reservation(Reservation reservation){
        reservationRepository.save(reservation);
        return reservation.getReservationId();

    }


    public void reservationDelete(Reservation reservation){

        reservationRepository.delete(reservation);

    }
}
