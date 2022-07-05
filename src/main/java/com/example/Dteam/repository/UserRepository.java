package com.example.Dteam.repository;

import com.example.Dteam.domain.Seat;
import com.example.Dteam.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private  EntityManager em;

    public void save(User user){
        em.persist(user);
        em.flush();
    }

    public User findOne(String userId) {
        return em.find(User.class, userId);
    }


    public List<User> findByLoginId(String userId) {          //아이디로 회원찾기
        return em.createQuery("select m from Us" +
                        "er m where m.userId = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    public List<User> findAll() {
        return em.createQuery("select i from User i", User.class)
                .getResultList();
    }

}
