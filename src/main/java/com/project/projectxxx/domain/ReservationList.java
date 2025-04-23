package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ReservationList {
    @Id @GeneratedValue
    @Column(name = "reservationList_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    
}
