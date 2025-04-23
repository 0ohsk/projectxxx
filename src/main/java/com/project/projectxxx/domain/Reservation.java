package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;
    private Date reservationDate;
    private int price;
    private int totalPrice;
    @Embedded
    private Period period;
    @Enumerated(EnumType.STRING)
    private CheckStatus status;
    @OneToMany(mappedBy = "reservationList_id")
    private Reservation reservation;
}
