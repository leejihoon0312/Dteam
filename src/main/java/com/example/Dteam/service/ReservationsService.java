package com.example.Dteam.service;

import com.example.Dteam.domain.Reservation;
import com.example.Dteam.repository.ReservationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationsService {

    private final ReservationsRepository reservationsRepository;

    //예약 등록
    public Long reservation(Reservation reservation){
        reservationsRepository.save(reservation);
        reservationsRepository.flush();
        return reservation.getReservationId();
    }

    //예약 리스트
    public List<Reservation> reservationList(){return reservationsRepository.findAll();}

    //예약 번호 찾기
    public Optional<Reservation> reservationFindById(Long reservationId){return reservationsRepository.findById(reservationId);}

    //예약 삭제
    public void reservationDelete(Long reservationId){reservationsRepository.deleteById(reservationId);}
}
