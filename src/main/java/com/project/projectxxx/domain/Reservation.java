package com.project.projectxxx.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.logging.log4j.util.Lazy;

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

    @Enumerated(EnumType.STRING)
    private CheckStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
