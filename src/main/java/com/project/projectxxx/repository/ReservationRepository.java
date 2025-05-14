package com.project.projectxxx.repository;

import com.project.projectxxx.domain.Reservation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReservationRepository {
    private final EntityManager em;

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }
    public List<Reservation> findAll(){
        List<Reservation> result = em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
        return result;
    }

    public Long Save(Reservation reservation){
        em.persist(reservation);
        return reservation.getId();
    }
}
