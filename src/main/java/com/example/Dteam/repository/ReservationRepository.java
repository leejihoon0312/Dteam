package com.example.Dteam.repository;

import com.example.Dteam.domain.Reservation;
import com.example.Dteam.domain.Seat;
import com.example.Dteam.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {
    private final EntityManager em;

    public void save(Reservation reservation){em.persist(reservation);}

    public List<Reservation> findAllReservation() {
        boolean state=true;
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    @Transactional
    public void delete(Reservation reservation){
        em.find(Reservation.class,reservation.getReservationId());
        em.remove(reservation);
    }

    public List<Reservation> findByLoginId(User user) {          //아이디로 회원찾기
        return em.createQuery("select r from Reservation r where r.user = :user", Reservation.class)
                .setParameter("user", user)
                .getResultList();
    }

}
