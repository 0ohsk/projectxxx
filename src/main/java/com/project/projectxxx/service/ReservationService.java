package com.project.projectxxx.service;

import com.project.projectxxx.domain.Reservation;
import com.project.projectxxx.domain.User;
import com.project.projectxxx.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository repository;

    public Reservation findReservationOne(Long id){
        Reservation reservationOne = repository.findOne(id);
        return reservationOne;
    }

    public Long reservationSave(Reservation reservation){
        repository.Save(reservation);
        return reservation.getId();
    }
}
