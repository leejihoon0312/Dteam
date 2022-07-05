package com.example.Dteam.repository;

import com.example.Dteam.domain.Seat;

import com.example.Dteam.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatRepository {

    @PersistenceContext
    private final EntityManager em;


    public void save(Seat seat){
        em.persist(seat);
    }


    public List<Seat> findAll() {
        return em.createQuery("select i from Seat i", Seat.class)
                .getResultList();
    }

    public Seat findOne(Long seatNum) {
        return em.find(Seat.class, seatNum);
    }
}
